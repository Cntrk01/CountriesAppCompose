package com.mckstudio.countriesapp.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.window.layout.WindowMetricsCalculator
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun BannerAd() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        AndroidView(
            factory = { context ->
                val windowMetrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(context)

                val bounds = windowMetrics.bounds

                var adWidthPixels = context.resources.displayMetrics.widthPixels.toFloat()

                if (adWidthPixels == 0f) {
                    adWidthPixels = bounds.width().toFloat()
                }

                val density = context.resources.displayMetrics.density
                val adWidth = (adWidthPixels / density).toInt()

                AdView(context).apply {
                    setAdSize(
                        AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                            context,
                            adWidth
                        )
                    )
                    adUnitId = "ca-app-pub-3940256099942544/6300978111"
                    loadAd(AdRequest.Builder().build())
                }
            }
        )
    }
}