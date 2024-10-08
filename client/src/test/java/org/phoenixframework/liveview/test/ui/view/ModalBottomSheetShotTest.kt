package org.phoenixframework.liveview.test.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import org.junit.Test
import org.phoenixframework.liveview.constants.AlignmentValues
import org.phoenixframework.liveview.constants.Attrs.attrContentAlignment
import org.phoenixframework.liveview.constants.Attrs.attrSheetValue
import org.phoenixframework.liveview.constants.Attrs.attrStyle
import org.phoenixframework.liveview.constants.ComposableTypes.box
import org.phoenixframework.liveview.constants.ComposableTypes.modalBottomSheet
import org.phoenixframework.liveview.constants.ComposableTypes.text
import org.phoenixframework.liveview.constants.ModifierNames.modifierFillMaxSize
import org.phoenixframework.liveview.constants.ModifierNames.modifierFillMaxWidth
import org.phoenixframework.liveview.constants.ModifierNames.modifierHeight
import org.phoenixframework.liveview.constants.ModifierNames.modifierTestTag
import org.phoenixframework.liveview.constants.ModifierTypes.typeDp
import org.phoenixframework.liveview.constants.SheetValues.expanded
import org.phoenixframework.liveview.test.base.LiveViewComposableTest

@OptIn(ExperimentalMaterial3Api::class)
class ModalBottomSheetShotTest : LiveViewComposableTest() {
    @Test
    fun simpleModalBottomSheetTest() {
        val testTag = "sheet"
        compareNativeComposableWithTemplate(
            testTag = testTag,
            delayBeforeScreenshot = 1000,
            captureScreenImage = true,
            nativeComposable = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag(testTag)
                ) {
                    val sheetState = rememberModalBottomSheetState()
                    ModalBottomSheet(onDismissRequest = {}, sheetState = sheetState) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                        ) {
                            Text(text = "BottomSheet Content")
                        }
                    }
                    LaunchedEffect(Unit) {
                        sheetState.expand()
                    }
                }
            },
            template = """
                <$box $attrStyle="$modifierFillMaxSize();$modifierTestTag('$testTag')">
                  <$modalBottomSheet $attrSheetValue="$expanded">
                    <$box 
                      $attrContentAlignment="${AlignmentValues.center}" 
                      $attrStyle="$modifierFillMaxWidth();$modifierHeight($typeDp(200))">
                      <$text>BottomSheet Content</$text>
                    </$box>
                  </$modalBottomSheet>                
                </$box>
                """
        )
    }
}