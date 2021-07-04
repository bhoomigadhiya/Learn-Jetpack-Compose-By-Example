package com.example.jetpackcompose.customview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.runtime.remember

/**
 * This example needs some more work.
 */
class CustomViewPaintActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            CustomDrawableViewComponent()
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun CustomDrawableViewComponent() {
    DrawingBoardComposable()
}

data class Paths(
    val x: Float,
    val y: Float
)

@Composable
fun DrawingBoardComposable() {
    val paths = remember { mutableStateListOf<Paths>() }
    val draggableState = DraggableState {  }
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .draggable(
//                state = rememberDraggableState {
//                    paths += Paths(downPosition.x, downPosition.y)
//                }
//            )
//            .dragGestureFilter(
//            startDragImmediately = true,
//            dragObserver = object : DragObserver {
//                override fun onStart(downPosition: Offset) {
//                    super.onStart(downPosition)
//
//                }
//            })
    ) {
        Canvas(modifier = Modifier) {
            val p = Path()
            for (path in paths) {
                p.lineTo(path.x, path.y)
                p.moveTo(path.x, path.y)
            }
            drawPath(p, color = Color.Black, style = Stroke(width = 3f, join = StrokeJoin.Round))
        }
    }
}
