package org.phoenixframework.liveview.data.dto

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import org.phoenixframework.liveview.data.core.CoreAttribute
import org.phoenixframework.liveview.data.core.CoreNodeElement
import org.phoenixframework.liveview.domain.base.ComposableBuilder
import org.phoenixframework.liveview.domain.base.ComposableTypes
import org.phoenixframework.liveview.domain.base.ComposableView
import org.phoenixframework.liveview.domain.base.ComposableViewFactory
import org.phoenixframework.liveview.domain.base.PushEvent
import org.phoenixframework.liveview.domain.extensions.toColor
import org.phoenixframework.liveview.domain.factory.ComposableTreeNode
import org.phoenixframework.liveview.ui.phx_components.PhxLiveView
import org.phoenixframework.liveview.ui.phx_components.paddingIfNotNull

class ScaffoldDTO private constructor(builder: Builder) :
    ComposableView(modifier = builder.modifier) {
    private val backgroundColor: Color = builder.backgroundColor

    @Composable
    override fun Compose(
        composableNode: ComposableTreeNode?,
        paddingValues: PaddingValues?,
        pushEvent: PushEvent,
    ) {
        val topBar = remember(composableNode?.children) {
            composableNode?.children?.find { it.node?.tag == ComposableTypes.topAppBar }
        }
        val body = remember(composableNode?.children) {
            composableNode?.children?.find { it.node?.tag != ComposableTypes.topAppBar }
        }
        Scaffold(
            modifier = modifier.paddingIfNotNull(paddingValues),
            backgroundColor = backgroundColor,
            topBar = {
                topBar?.let { appBar ->
                    PhxLiveView(appBar, paddingValues, pushEvent)
                }
            },
            content = { contentPaddingValues ->
                body?.let { content ->
                    PhxLiveView(content, contentPaddingValues, pushEvent)
                }
            }
        )
    }

    class Builder : ComposableBuilder() {
        var backgroundColor: Color = Color.White

        fun backgroundColor(color: String) = apply {
            if (color.isNotEmpty()) {
                this.backgroundColor = color.toColor()
            }
        }

        fun build() = ScaffoldDTO(this)
    }
}

object ScaffoldDtoFactory : ComposableViewFactory<ScaffoldDTO, ScaffoldDTO.Builder>() {
    override fun buildComposableView(
        attributes: List<CoreAttribute>,
        children: List<CoreNodeElement>?,
        pushEvent: PushEvent?,
    ): ScaffoldDTO = attributes.fold(ScaffoldDTO.Builder()) { builder, attribute ->
        when (attribute.name) {
            "backgroundColor" -> builder.backgroundColor(attribute.value)
            "size" -> builder.size(attribute.value)
            "height" -> builder.height(attribute.value)
            "width" -> builder.width(attribute.value)
            "padding" -> builder.padding(attribute.value)
            "horizontalPadding" -> builder.horizontalPadding(attribute.value)
            "verticalPadding" -> builder.verticalPadding(attribute.value)
            else -> builder
        } as ScaffoldDTO.Builder
    }.build()
}