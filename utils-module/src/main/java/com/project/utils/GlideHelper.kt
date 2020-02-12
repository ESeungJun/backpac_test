package com.project.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

object GlideHelper {


    interface OnException {

        fun onException(e: Exception?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean

    }

    interface OnResourceReady {

        fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean

    }


    fun load(imageView: ImageView, url: String){
        val option = createGlideRequestOption(null, null)
        createGlideRequestBitmapFadeInBuilder(imageView.context, option, null, null)
            .load(url)
            .into(imageView)
    }

    fun load(imageView: ImageView, url: String, error: OnException, ready: OnResourceReady){
        val option = createGlideRequestOption(null, null)
        createGlideRequestBitmapFadeInBuilder(imageView.context, option, error, ready)
            .load(url)
            .into(imageView)
    }

    fun loadNoAnimation(imageView: ImageView, url: String){
        val option = createGlideRequestOption(null, null)
        createGlideRequestBitmapBuilder(imageView.context, option, null, null)
            .load(url)
            .into(imageView)
    }

    fun loadNoAnimation(imageView: ImageView, url: String, error: OnException, ready: OnResourceReady){
        val option = createGlideRequestOption(null, null)
        createGlideRequestBitmapBuilder(imageView.context, option, error, ready)
            .load(url)
            .into(imageView)
    }


    private fun createGlideRequestBitmapFadeInBuilder(context: Context, requestOptions: RequestOptions, error: OnException?, ready: OnResourceReady?) =
        Glide.with(context)
            .asBitmap()
            .apply(requestOptions)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .thumbnail(0.25f)
            .transition(GenericTransitionOptions.with(R.anim.normal_fade_in))
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean
                ): Boolean {
                    Logger.error("GlideHelper Error", " > $e, $target")
                    return error?.onException(e, model, target, isFirstResource) ?: false
                }

                override fun onResourceReady(
                    resource: Bitmap?, model: Any?, target: Target<Bitmap>?,
                    dataSource: DataSource?, isFirstResource: Boolean
                ): Boolean {
                    Logger.log("GlideHelper onResourceReady", " > $model")
                    return ready?.onResourceReady(resource, model, target, dataSource, isFirstResource) ?: false
                }

            })


    private fun createGlideRequestBitmapBuilder(context: Context, requestOptions: RequestOptions, error: OnException?, ready: OnResourceReady?) =
        Glide.with(context)
            .asBitmap()
            .apply(requestOptions)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .thumbnail(0.25f)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean
                ): Boolean {
                    Logger.error("GlideHelper Error", " > $e, $target")
                    return error?.onException(e, model, target, isFirstResource) ?: false
                }

                override fun onResourceReady(
                    resource: Bitmap?, model: Any?, target: Target<Bitmap>?,
                    dataSource: DataSource?, isFirstResource: Boolean
                ): Boolean {
                    Logger.log("GlideHelper onResourceReady", " > $model")
                    return ready?.onResourceReady(resource, model, target, dataSource, isFirstResource) ?: false
                }

            })


    private fun createGlideRequestOption(placeHolder: Int?, errorHolder: Int?) =
        RequestOptions()
            .placeholder(placeHolder?: 0)
            .error(errorHolder?: 0)
            .centerCrop()




}