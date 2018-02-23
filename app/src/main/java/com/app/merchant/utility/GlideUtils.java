package com.app.merchant.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.app.merchant.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;

/**
 * Created by rajnikant on 03/10/17.
 */

public class GlideUtils {
    private static String TAG = GlideUtils.class.getSimpleName();

    //load image with progressbar
    public static void loadImageUsingGlide(Context mContext, String imageUrl, ImageView imageView, final ProgressBar progressBar) {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
        Glide.with(mContext).load(imageUrl).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
                return false;
            }
        }).fitCenter().into(imageView);
    }

    //load image without progressbar and placeholder
    public static void loadImageUsingGlide(Context mContext, String imageUrl, ImageView imageView) {

        Glide.with(mContext).load(imageUrl).into(imageView);
    }

    //load simple image with progress bar
    public static void loadImage(final Context mContext, String imageUrl, final ImageView imageView, final ProgressBar progressBar, final int placeholder) {
        showProgressBar(progressBar);
        /*try {*/
        Glide.with(mContext).load(imageUrl).asBitmap().placeholder(placeholder)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                imageView.setImageResource(placeholder);
                hideProgressBar(progressBar);
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                hideProgressBar(progressBar);
                return false;
            }
        }).into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                imageView.setImageBitmap(resource);
            }
        });
        /*}catch (Exception ex){
            hideProgressBar(progressBar);
        }*/
    }
    //load image using glide with rounded corner center crop with placeholder
    public static void loadImageRoundedCorner(final Context mContext, String imageUrl, final ImageView imageView,
                                              final ProgressBar progressBar, final int placeHolder,int cornerRadius) {
        showProgressBar(progressBar);
        Glide.with(mContext).load(imageUrl).asBitmap().placeholder(placeHolder)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                imageView.setImageResource(placeHolder);
                hideProgressBar(progressBar);
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                hideProgressBar(progressBar);
                return false;
            }
        }).centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable roundedBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                roundedBitmapDrawable.setCornerRadius(convertDpToPx(cornerRadius, mContext));
                roundedBitmapDrawable.setAntiAlias(true);
                imageView.setImageDrawable(roundedBitmapDrawable);
                //progressBarVisibility(progressBar);
            }
        });
    }
    //Circular Image With progressbar and placeholder
    public static void loadImageProfilePic(final Context mContext, String imageUrl, final ImageView imageView, final ProgressBar progressBar, final int placeholder) {
        try {
            showProgressBar(progressBar);
            Glide.with(mContext).load(imageUrl).asBitmap().placeholder(placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, Bitmap>() {
                @Override
                public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                    imageView.setImageResource(placeholder);
                    hideProgressBar(progressBar);
                    return false;
                }

                @Override
                public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    hideProgressBar(progressBar);
                    return false;
                }
            }).into(new BitmapImageViewTarget(imageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable roundedBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                    roundedBitmapDrawable.setCircular(true);
                    imageView.setImageDrawable(roundedBitmapDrawable);
                    //progressBarVisibility(progressBar);
                }
            });
        } catch (Exception ex) {
            LogUtils.LOGE(TAG, ex.toString());
        }
    }

    //load image using glide with rounded corner center crop with placeholder
    public static void loadImageRoundedCorner(final Context mContext, String imageUrl, final ImageView imageView, final ProgressBar progressBar, final int placeHolder) {
        showProgressBar(progressBar);
        Glide.with(mContext).load(imageUrl).asBitmap().placeholder(placeHolder)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                imageView.setImageResource(placeHolder);
                hideProgressBar(progressBar);
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                hideProgressBar(progressBar);
                return false;
            }
        }).centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable roundedBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                roundedBitmapDrawable.setCornerRadius(convertDpToPx(6, mContext));
                roundedBitmapDrawable.setAntiAlias(true);
                imageView.setImageDrawable(roundedBitmapDrawable);
                //progressBarVisibility(progressBar);
            }
        });
    }


    //load image using glide with rounded corner center crop with placeholder
    public static void loadFeedRoundedCorner(final Context mContext, String imageUrl, final ImageView imageView, final ProgressBar progressBar, final int placeHolder, final int roundCorner) {
        showProgressBar(progressBar);
        Glide.with(mContext).load(imageUrl).asBitmap().placeholder(placeHolder)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                imageView.setImageResource(placeHolder);
                hideProgressBar(progressBar);
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                hideProgressBar(progressBar);
                return false;
            }
        }).into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable roundedBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                roundedBitmapDrawable.setCornerRadius(convertDpToPx(roundCorner, mContext));
                roundedBitmapDrawable.setAntiAlias(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setImageDrawable(roundedBitmapDrawable);
                //progressBarVisibility(progressBar);
            }
        });
    }

    //load image using glide with rounded corner center crop without placeholder
    public static void loadImageRoundedCorner(final Context mContext, String imageUrl, final ImageView imageView) {
        Glide.with(mContext).load(imageUrl).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        }).centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable roundedBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                roundedBitmapDrawable.setCornerRadius(convertDpToPx(5, mContext));
                roundedBitmapDrawable.setAntiAlias(true);
                imageView.setImageDrawable(roundedBitmapDrawable);
            }
        });
    }

    //load image using glide with two rounded corner
    public static void loadImageTwoRoundedCorner(final Context mContext, String imageUrl, final ImageView imageView, final ProgressBar progressBar, final int placeHolder) {
        showProgressBar(progressBar);
        try {
            Glide.with(mContext).load(imageUrl).asBitmap().placeholder(placeHolder)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, Bitmap>() {
                @Override
                public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                    imageView.setImageResource(placeHolder);
                    hideProgressBar(progressBar);
                    return false;
                }

                @Override
                public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    hideProgressBar(progressBar);
                    return false;
                }
            }).centerCrop().into(new BitmapImageViewTarget(imageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    hideProgressBar(progressBar);
                    if (resource != null) {
                        Bitmap bm = createRoundedRectBitmap(resource, convertDpToPx(12, mContext), 0, 0, convertDpToPx(12, mContext));
                        imageView.setImageBitmap(bm);
                    }
                }
            });
        } catch (Exception ex) {
            hideProgressBar(progressBar);
        }

    }

    private static Bitmap createRoundedRectBitmap(@NonNull Bitmap bitmap,
                                                  float topLeftCorner, float topRightCorner,
                                                  float bottomRightCorner, float bottomLeftCorner) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = Color.WHITE;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        Path path = new Path();
        float[] radii = new float[]{
                topLeftCorner, bottomLeftCorner,
                topRightCorner, topRightCorner,
                bottomRightCorner, bottomRightCorner,
                bottomLeftCorner, bottomLeftCorner
        };

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        path.addRoundRect(rectF, radii, Path.Direction.CW);
        canvas.drawPath(path, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    private static void showProgressBar(ProgressBar progressBar) {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private static void hideProgressBar(ProgressBar progressBar) {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }


    public static int convertDpToPx(int dp, Context context) {
        return Math.round(dp * (context.getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));

    }

    //load simple image with progress bar
    public static void loadImageWithZoom(final Context mContext, String imageUrl, final ImageView imageView) {
        final CircularAnimatedDrawable animPlaceholder =
                CircularAnimatedDrawable.getInstance(mContext.getResources().getColor(R.color.progresscolor), 6);
        animPlaceholder.start();
        Glide.with(mContext)
                .load(imageUrl)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .placeholder(animPlaceholder)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        super.onResourceReady(resource, glideAnimation);
                        animPlaceholder.stop();
                    }

                    @Override
                    public void onLoadCleared(Drawable placeholder) {
                    }

                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                    }
                });
    }

}
