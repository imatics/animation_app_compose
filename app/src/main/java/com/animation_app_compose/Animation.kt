package com.animation_app_compose

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.delay
import kotlin.math.pow

enum class Direction{Vertical, Horizontal}
val CustomEasing = Easing { fraction -> getInterpolation(fraction) }


@Composable
fun Animation(
    delay: Long = 0,
    direction: Direction = Direction.Vertical,
    offset: Int = 50,
    duration: Int = 1200,
    content: @Composable BoxScope.() -> Unit
) {

    val animationValue = remember { androidx.compose.animation.core.Animatable(0f) }
    val show = remember { mutableStateOf(false) }

    val alpha: Float by animateFloatAsState(
        targetValue = if(show.value) 1f else 0f, animationSpec = tween(
            durationMillis = 800,
            easing = CustomEasing
        ),
        label = ""
    )



    LaunchedEffect(true) {
        delay(100 + delay)
        show.value = true
        animationValue.animateTo(1f, animationSpec = tween(durationMillis = 800))
    }

    Box(
        modifier = Modifier
            .offset {
                if (direction == Direction.Vertical) {
                    IntOffset(
                        x = 0,
                        y = androidx.compose.ui.util.lerp(
                            start = offset,
                            stop = 0,
                            animationValue.value
                        )
                    )
                } else {
                    IntOffset(
                        y = 0,
                        x = androidx.compose.ui.util.lerp(
                            start = offset,
                            stop = 0,
                            animationValue.value
                        )
                    )
                }
            }
            .alpha(alpha = alpha),
    ){
        content()
    }

}


@Composable
fun FadeScaleAnimation(
    delay: Long = 0,
    direction: Direction = Direction.Vertical,
    offset: Int = 50,
    duration: Int = 1200,
    content: @Composable BoxScope.() -> Unit
) {

    val animationValue = remember { androidx.compose.animation.core.Animatable(0f) }
    val show = remember { mutableStateOf(false) }

    val alpha: Float by animateFloatAsState(
        targetValue = if(show.value) 1f else 0f, animationSpec = tween(
            durationMillis = 800,
            easing = CustomEasing
        ),
        label = ""
    )



    LaunchedEffect(true) {
        delay(100 + delay)
        show.value = true
        animationValue.animateTo(1f, animationSpec = tween(durationMillis = 800))
    }
    Box(
        modifier = Modifier
            .offset {
                if (direction == Direction.Vertical) {
                    IntOffset(
                        x = 0,
                        y = androidx.compose.ui.util.lerp(
                            start = offset,
                            stop = 0,
                            animationValue.value
                        )
                    )
                } else {
                    IntOffset(
                        y = 0,
                        x = androidx.compose.ui.util.lerp(
                            start = offset,
                            stop = 0,
                            animationValue.value
                        )
                    )
                }
            }
            .alpha(alpha = alpha),
    ){
        content()
    }

}


 fun getInterpolation(input: Float): Float {
     val mFactor = 1f;
    val result = if (mFactor == 1.0f) {
        (1.0f - (1.0f - input) * (1.0f - input))
    } else {
        (1.0f - (1.0f - input).pow((2 * mFactor)))
    }
    return result
}


