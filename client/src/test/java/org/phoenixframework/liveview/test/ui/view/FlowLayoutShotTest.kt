package org.phoenixframework.liveview.test.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.junit.Test
import org.phoenixframework.liveview.constants.Attrs.attrHorizontalArrangement
import org.phoenixframework.liveview.constants.Attrs.attrMaxItemsInEachColumn
import org.phoenixframework.liveview.constants.Attrs.attrMaxItemsInEachRow
import org.phoenixframework.liveview.constants.Attrs.attrStyle
import org.phoenixframework.liveview.constants.Attrs.attrVerticalArrangement
import org.phoenixframework.liveview.constants.ComposableTypes.flowColumn
import org.phoenixframework.liveview.constants.ComposableTypes.flowRow
import org.phoenixframework.liveview.constants.ComposableTypes.outlinedButton
import org.phoenixframework.liveview.constants.ComposableTypes.text
import org.phoenixframework.liveview.constants.HorizontalArrangementValues
import org.phoenixframework.liveview.constants.ModifierNames.modifierFillMaxSize
import org.phoenixframework.liveview.constants.ModifierNames.modifierHeight
import org.phoenixframework.liveview.constants.ModifierTypes.typeDp
import org.phoenixframework.liveview.constants.VerticalArrangementValues
import org.phoenixframework.liveview.test.base.LiveViewComposableTest

@OptIn(ExperimentalLayoutApi::class)
class FlowLayoutShotTest : LiveViewComposableTest() {
    @Test
    fun simpleFlowColumnTest() {
        val range = 0..30
        val childrenForTemplate = range.fold("") { s, i ->
            "$s<$outlinedButton><$text>Item $i</$text></$outlinedButton>"
        }
        compareNativeComposableWithTemplate(
            nativeComposable = {
                FlowColumn(Modifier.height(400.dp)) {
                    range.forEach {
                        OutlinedButton(onClick = {}) {
                            Text(text = "Item $it")
                        }
                    }
                }
            },
            template = """
                <$flowColumn $attrStyle="$modifierHeight($typeDp(400))">
                    $childrenForTemplate
                </$flowColumn>
                """
        )
    }

    @Test
    fun simpleFlowRowTest() {
        val range = 0..30
        val childrenForTemplate = range.fold("") { s, i ->
            "$s<$outlinedButton><$text>Item $i</$text></$outlinedButton>"
        }
        compareNativeComposableWithTemplate(
            nativeComposable = {
                FlowRow {
                    range.forEach {
                        OutlinedButton(onClick = {}) {
                            Text(text = "Item $it")
                        }
                    }
                }
            },
            template = """
                <$flowRow>
                  $childrenForTemplate
                </$flowRow>
                """
        )
    }

    @Test
    fun flowColumnWithMaxItemsInEachColumnTest() {
        val range = 0..30
        val childrenForTemplate = range.fold("") { s, i ->
            "$s<$outlinedButton><$text>Item $i</$text></$outlinedButton>"
        }
        compareNativeComposableWithTemplate(
            nativeComposable = {
                FlowColumn(maxItemsInEachColumn = 5) {
                    range.forEach {
                        OutlinedButton(onClick = {}) {
                            Text(text = "Item $it")
                        }
                    }
                }
            },
            template = """
                <$flowColumn $attrMaxItemsInEachColumn="5">
                  $childrenForTemplate
                </$flowColumn>
                """
        )
    }

    @Test
    fun flowRowWithMaxItemsInEachRowTest() {
        val range = 0..30
        val childrenForTemplate = range.fold("") { s, i ->
            "$s<$outlinedButton><$text>Item $i</$text></$outlinedButton>"
        }
        compareNativeComposableWithTemplate(
            nativeComposable = {
                FlowRow(maxItemsInEachRow = 3) {
                    range.forEach {
                        OutlinedButton(onClick = {}) {
                            Text(text = "Item $it")
                        }
                    }
                }
            },
            template = """
                <$flowRow $attrMaxItemsInEachRow="3">
                  $childrenForTemplate
                </$flowRow>
                """
        )
    }

    @Test
    fun flowColumnWithArrangementTest() {
        val range = 0..12
        val childrenForTemplate = range.fold("") { s, i ->
            "$s<$outlinedButton><$text>Item $i</$text></$outlinedButton>"
        }
        compareNativeComposableWithTemplate(
            nativeComposable = {
                FlowColumn(
                    modifier = Modifier.fillMaxSize(),
                    maxItemsInEachColumn = 5,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalArrangement = Arrangement.Center,
                ) {
                    range.forEach {
                        OutlinedButton(onClick = {}) {
                            Text(text = "Item $it")
                        }
                    }
                }
            },
            template = """
                <$flowColumn 
                  $attrStyle="$modifierFillMaxSize()" $attrMaxItemsInEachColumn="5" 
                  $attrHorizontalArrangement="${HorizontalArrangementValues.spaceEvenly}" 
                  $attrVerticalArrangement="${VerticalArrangementValues.center}">
                    $childrenForTemplate
                </$flowColumn>
                """
        )
    }

    @Test
    fun flowRowWithArrangementTest() {
        val range = 0..12
        val childrenForTemplate = range.fold("") { s, i ->
            "$s<$outlinedButton><$text>Item $i</$text></$outlinedButton>"
        }
        compareNativeComposableWithTemplate(
            nativeComposable = {
                FlowRow(
                    modifier = Modifier.fillMaxSize(),
                    maxItemsInEachRow = 3,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalArrangement = Arrangement.Bottom,
                ) {
                    range.forEach {
                        OutlinedButton(onClick = {}) {
                            Text(text = "Item $it")
                        }
                    }
                }
            },
            template = """
                <$flowRow $attrStyle="$modifierFillMaxSize()" $attrMaxItemsInEachRow="3" 
                  $attrHorizontalArrangement="${HorizontalArrangementValues.spaceBetween}" 
                  $attrVerticalArrangement="${VerticalArrangementValues.bottom}">
                    $childrenForTemplate
                </$flowRow>
                """
        )
    }
}