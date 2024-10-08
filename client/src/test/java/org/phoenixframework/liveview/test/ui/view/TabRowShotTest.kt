package org.phoenixframework.liveview.test.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.junit.Test
import org.phoenixframework.liveview.constants.Attrs.attrContainerColor
import org.phoenixframework.liveview.constants.Attrs.attrContentColor
import org.phoenixframework.liveview.constants.Attrs.attrEdgePadding
import org.phoenixframework.liveview.constants.Attrs.attrEnabled
import org.phoenixframework.liveview.constants.Attrs.attrSelected
import org.phoenixframework.liveview.constants.Attrs.attrSelectedTabIndex
import org.phoenixframework.liveview.constants.Attrs.attrStyle
import org.phoenixframework.liveview.constants.Attrs.attrTemplate
import org.phoenixframework.liveview.constants.ComposableTypes.box
import org.phoenixframework.liveview.constants.ComposableTypes.scrollableTabRow
import org.phoenixframework.liveview.constants.ComposableTypes.tab
import org.phoenixframework.liveview.constants.ComposableTypes.tabRow
import org.phoenixframework.liveview.constants.ComposableTypes.text
import org.phoenixframework.liveview.constants.ModifierNames.modifierBackground
import org.phoenixframework.liveview.constants.ModifierNames.modifierClip
import org.phoenixframework.liveview.constants.ModifierNames.modifierFillMaxWidth
import org.phoenixframework.liveview.constants.ModifierNames.modifierHeight
import org.phoenixframework.liveview.constants.ModifierTypes.typeColor
import org.phoenixframework.liveview.constants.ModifierTypes.typeDp
import org.phoenixframework.liveview.constants.ShapeValues.circle
import org.phoenixframework.liveview.constants.SystemColorValues.Blue
import org.phoenixframework.liveview.constants.SystemColorValues.Cyan
import org.phoenixframework.liveview.constants.SystemColorValues.Yellow
import org.phoenixframework.liveview.constants.Templates.templateDivider
import org.phoenixframework.liveview.constants.Templates.templateText
import org.phoenixframework.liveview.test.base.LiveViewComposableTest

class TabRowShotTest : LiveViewComposableTest() {
    @Test
    fun tabRowWithCustomColorsTest() {
        compareNativeComposableWithTemplate(
            nativeComposable = {
                TabRow(
                    selectedTabIndex = 0,
                    containerColor = Color.Blue,
                    contentColor = Color.Yellow,
                ) {
                    Tab(
                        selected = true,
                        onClick = {},
                        text = { Text("Tab 0") },
                    )
                    Tab(
                        selected = false,
                        onClick = {},
                        text = { Text("Tab 1") },
                    )
                    Tab(
                        selected = false,
                        enabled = false,
                        onClick = {},
                        text = { Text("Tab 2") },
                    )
                }
            },
            template = """
                <$tabRow $attrSelectedTabIndex="0" $attrContainerColor="$Blue" 
                  $attrContentColor="$Yellow" >
                    <$tab $attrSelected="true">
                        <$text $attrTemplate="$templateText">Tab 0</$text>
                    </$tab>
                    <$tab $attrSelected="false">
                        <$text $attrTemplate="$templateText">Tab 1</$text>
                    </$tab>      
                    <$tab $attrSelected="false" $attrEnabled="false">
                        <$text $attrTemplate="$templateText">Tab 2</$text>
                    </$tab>                                    
                </$tabRow>
                """
        )
    }

    @Test
    fun tabRowWithCustomDividerTest() {
        compareNativeComposableWithTemplate(
            nativeComposable = {
                TabRow(
                    selectedTabIndex = 0,
                    containerColor = Color.Blue,
                    contentColor = Color.Yellow,
                    divider = {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                                .clip(CircleShape)
                                .background(Color.Cyan)
                        )
                    }
                ) {
                    Tab(
                        selected = true,
                        onClick = {},
                        text = { Text("Tab 0") },
                    )
                    Tab(
                        selected = false,
                        onClick = {},
                        text = { Text("Tab 1") },
                    )
                    Tab(
                        selected = false,
                        enabled = false,
                        onClick = {},
                        text = { Text("Tab 2") },
                    )
                }
            },
            template = """
                <$tabRow 
                  $attrSelectedTabIndex="0" 
                  $attrContainerColor="$Blue" 
                  $attrContentColor="$Yellow" >
                  <$box 
                    $attrTemplate="$templateDivider" 
                    $attrStyle="$modifierFillMaxWidth();$modifierHeight($typeDp(4));$modifierClip($circle);$modifierBackground($typeColor.$Cyan)" />
                  <$tab $attrSelected="true">
                    <$text $attrTemplate="$templateText">Tab 0</$text>
                  </$tab>
                  <$tab $attrSelected="false">
                    <$text $attrTemplate="$templateText">Tab 1</$text>
                  </$tab>      
                  <$tab $attrSelected="false" $attrEnabled="false">
                    <$text $attrTemplate="$templateText">Tab 2</$text>
                  </$tab>                                    
                </$tabRow>
                """
        )
    }

    @Test
    fun simpleScrollableTabRowTest() {
        compareNativeComposableWithTemplate(
            nativeComposable = {
                ScrollableTabRow(selectedTabIndex = 1) {
                    Tab(
                        selected = false,
                        onClick = {},
                        text = { Text("Tab 0") },
                    )
                    Tab(
                        selected = true,
                        onClick = {},
                        text = { Text("Tab 1") },
                    )
                    Tab(
                        selected = false,
                        enabled = false,
                        onClick = {},
                        text = { Text("Tab 2") },
                    )
                    Tab(
                        selected = false,
                        enabled = false,
                        onClick = {},
                        text = { Text("Tab 3") },
                    )
                }
            },
            template = """
                <$scrollableTabRow $attrSelectedTabIndex="1" >
                    <$tab $attrSelected="false">
                        <$text $attrTemplate="$templateText">Tab 0</$text>
                    </$tab>
                    <$tab $attrSelected="true">
                        <$text $attrTemplate="$templateText">Tab 1</$text>
                    </$tab>      
                    <$tab $attrSelected="false" $attrEnabled="false">
                        <$text $attrTemplate="$templateText">Tab 2</$text>
                    </$tab>         
                    <$tab $attrSelected="false" $attrEnabled="false">
                        <$text $attrTemplate="$templateText">Tab 3</$text>
                    </$tab>                                                  
                </$scrollableTabRow>
                """
        )
    }

    @Test
    fun scrollableTabRowWithEdgePaddingTest() {
        compareNativeComposableWithTemplate(
            nativeComposable = {
                ScrollableTabRow(selectedTabIndex = 1, edgePadding = 8.dp) {
                    Tab(
                        selected = false,
                        onClick = {},
                        text = { Text("Tab 0") },
                    )
                    Tab(
                        selected = true,
                        onClick = {},
                        text = { Text("Tab 1") },
                    )
                    Tab(
                        selected = false,
                        enabled = false,
                        onClick = {},
                        text = { Text("Tab 2") },
                    )
                    Tab(
                        selected = false,
                        enabled = false,
                        onClick = {},
                        text = { Text("Tab 3") },
                    )
                }
            },
            template = """
                <$scrollableTabRow $attrSelectedTabIndex="1" $attrEdgePadding="8">
                    <$tab $attrSelected="false">
                        <$text $attrTemplate="$templateText">Tab 0</$text>
                    </$tab>
                    <$tab $attrSelected="true">
                        <$text $attrTemplate="$templateText">Tab 1</$text>
                    </$tab>      
                    <$tab $attrSelected="false" $attrEnabled="false">
                        <$text $attrTemplate="$templateText">Tab 2</$text>
                    </$tab>         
                    <$tab $attrSelected="false" $attrEnabled="false">
                        <$text $attrTemplate="$templateText">Tab 3</$text>
                    </$tab>                                                  
                </$scrollableTabRow>
                """
        )
    }

    @Test
    fun scrollableTabRowWithCustomColorsTest() {
        compareNativeComposableWithTemplate(
            nativeComposable = {
                ScrollableTabRow(
                    selectedTabIndex = 0,
                    containerColor = Color.Blue,
                    contentColor = Color.Yellow,
                ) {
                    Tab(
                        selected = true,
                        onClick = {},
                        text = { Text("Tab 0") },
                    )
                    Tab(
                        selected = false,
                        onClick = {},
                        text = { Text("Tab 1") },
                    )
                    Tab(
                        selected = false,
                        enabled = false,
                        onClick = {},
                        text = { Text("Tab 2") },
                    )
                    Tab(
                        selected = false,
                        enabled = false,
                        onClick = {},
                        text = { Text("Tab 3") },
                    )
                }
            },
            template = """
                <$scrollableTabRow $attrSelectedTabIndex="0" 
                    $attrContainerColor="$Blue" $attrContentColor="$Yellow" >
                    <$tab $attrSelected="true">
                        <$text $attrTemplate="$templateText">Tab 0</$text>
                    </$tab>
                    <$tab $attrSelected="false">
                        <$text $attrTemplate="$templateText">Tab 1</$text>
                    </$tab>      
                    <$tab $attrSelected="false" $attrEnabled="false">
                        <$text $attrTemplate="$templateText">Tab 2</$text>
                    </$tab>         
                    <$tab $attrSelected="false" $attrEnabled="false">
                        <$text $attrTemplate="$templateText">Tab 3</$text>
                    </$tab>                                                  
                </$scrollableTabRow>
                """
        )
    }
}