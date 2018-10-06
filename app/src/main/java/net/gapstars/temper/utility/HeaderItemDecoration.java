package net.gapstars.temper.utility;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.gapstars.temper.R;

import io.reactivex.annotations.NonNull;

class HeaderItemDecoration extends RecyclerView.ItemDecoration {

    private final int headerOffset;
    private final boolean sticky;
    private final SectionCallback sectionCallback;

    private View headerView;
    private TextView header;

    public HeaderItemDecoration(int headerHeight, boolean sticky, @NonNull SectionCallback sectionCallback) {
        headerOffset = headerHeight;
        this.sticky = sticky;
        this.sectionCallback = sectionCallback;
    }

    @Override
    public void getItemOffsets(@android.support.annotation.NonNull Rect outRect,
                               @android.support.annotation.NonNull View view,
                               @android.support.annotation.NonNull RecyclerView parent,
                               @android.support.annotation.NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int pos = parent.getChildAdapterPosition(view);
        if (sectionCallback.isSection(pos)) {
            outRect.top = headerOffset;
        }
    }

    @Override
    public void onDrawOver(@android.support.annotation.NonNull Canvas c,
                           @android.support.annotation.NonNull RecyclerView parent,
                           @android.support.annotation.NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        if (headerView == null) {
            headerView = inflateHeaderView(parent);
            header = headerView.findViewById(R.id.tv_header);
            fixLayoutSize(headerView, parent);
        }

        CharSequence previousHeader = "";
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            final int position = parent.getChildAdapterPosition(child);

            CharSequence title = sectionCallback.getSectionHeader(position);
            header.setText(title);
            if (!previousHeader.equals(title) || sectionCallback.isSection(position)) {
                drawHeader(c, child, headerView);
                previousHeader = title;
            }
        }
    }

    private void drawHeader(Canvas c, View child, View headerView) {
        c.save();
        if (sticky) {
            c.translate(0, Math.max(0, child.getTop() - headerView.getHeight()));
        } else {
            c.translate(0, child.getTop() - headerView.getHeight());
        }
        headerView.draw(c);
        c.restore();
    }

    private View inflateHeaderView(RecyclerView parent) {
        return LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sticky_header, parent, false);
    }

    private void fixLayoutSize(View view, ViewGroup parent) {
        int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(),
                View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(),
                View.MeasureSpec.UNSPECIFIED);

        int childWidth = ViewGroup.getChildMeasureSpec(widthSpec,
                parent.getPaddingLeft() + parent.getPaddingRight(),
                view.getLayoutParams().width);
        int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
                parent.getPaddingTop() + parent.getPaddingBottom(),
                view.getLayoutParams().height);

        view.measure(childWidth, childHeight);

        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public interface SectionCallback {

        boolean isSection(int position);

        CharSequence getSectionHeader(int position);
    }
}
