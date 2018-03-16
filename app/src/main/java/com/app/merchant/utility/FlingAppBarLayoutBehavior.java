/*
 *
    Copyright (c) 2018 Virdrobe. All rights reserved. Developed by Appster.
    Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 *
 */

package com.app.merchant.utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import java.lang.ref.WeakReference;

public class FlingAppBarLayoutBehavior
        extends AppBarLayout.Behavior {

    // The minimum I have seen for a dy, after the recycler view stopped.
    private static final int MINIMUM_DELTA_Y = -4;

    @Nullable
    private
    RecyclerViewScrollListener mScrollListener;

    private boolean isPositive;

    public FlingAppBarLayoutBehavior() {
    }

    public FlingAppBarLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void callSuperOnNestedFling(
            CoordinatorLayout coordinatorLayout,
            AppBarLayout child,
            View target,
            float velocityY) {
        super.onNestedFling(
                coordinatorLayout,
                child,
                target,
                (float) 0,
                velocityY,
                false
        );
    }

    @Override
    public boolean onNestedFling(
            @NonNull CoordinatorLayout coordinatorLayout,
            @NonNull AppBarLayout child,
            @NonNull View target,
            float velocityX,
            float velocityY,
            boolean consumed) {

        if (velocityY > 0 && !isPositive || velocityY < 0 && isPositive) {
            velocityY = velocityY * -1;
        }

        if (target instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) target;

            if (mScrollListener == null) {
                mScrollListener = new RecyclerViewScrollListener(
                        coordinatorLayout,
                        child,
                        this
                );
                recyclerView.addOnScrollListener(mScrollListener);
            }

            mScrollListener.setVelocity(velocityY);
        }

        return super.onNestedFling(
                coordinatorLayout,
                child,
                target,
                velocityX,
                velocityY,
                consumed
        );
    }

    @Override
    public void onNestedPreScroll(
            @NonNull CoordinatorLayout coordinatorLayout,
            @NonNull AppBarLayout child,
            @NonNull View target,
            int dx,
            int dy,
            @NonNull int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        isPositive = dy > 0;
    }

    private static class RecyclerViewScrollListener
            extends RecyclerView.OnScrollListener {

        @NonNull
        private final WeakReference<AppBarLayout> mAppBarLayoutWeakReference;

        @NonNull
        private final WeakReference<FlingAppBarLayoutBehavior> mBehaviorWeakReference;

        @NonNull
        private final WeakReference<CoordinatorLayout> mCoordinatorLayoutWeakReference;

        private int mDy;

        private float mVelocity;

        public RecyclerViewScrollListener(
                @NonNull CoordinatorLayout coordinatorLayout,
                @NonNull AppBarLayout child,
                @NonNull FlingAppBarLayoutBehavior barBehavior) {
            mCoordinatorLayoutWeakReference = new WeakReference<>(coordinatorLayout);
            mAppBarLayoutWeakReference = new WeakReference<>(child);
            mBehaviorWeakReference = new WeakReference<>(barBehavior);
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                if (mDy < MINIMUM_DELTA_Y
                        && mAppBarLayoutWeakReference.get() != null
                        && mCoordinatorLayoutWeakReference.get() != null
                        && mBehaviorWeakReference.get() != null) {

                    // manually trigger the fling when it's scrolled at the top
                    mBehaviorWeakReference.get()
                            .callSuperOnNestedFling(
                                    mCoordinatorLayoutWeakReference.get(),
                                    mAppBarLayoutWeakReference.get(),
                                    recyclerView,
                                    mVelocity // TODO find a way to recalculate a correct velocity.
                            );

                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            mDy = dy;
        }

        public void setVelocity(float velocity) {
            mVelocity = velocity;
        }

    }

}