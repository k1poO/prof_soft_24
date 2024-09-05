package com.example.finalproject.ui.components.tool_bar

data class ToolBarType(
    val type: String = BASE_TYPE,
    val title: String = EMPTY_TITLE,
    val isVisible: Boolean = true,
    val isBackArrow: Boolean = false,
    val onIconClick: () -> Unit = {},
    val onBackClick: () -> Unit
) {
    companion object {

        const val EMPTY_TITLE = ""

        const val SEARCH_TYPE = "search_type"
        const val FAVOURITE_TYPE = "favourite_type"
        const val BASE_TYPE = "base_type"
        const val PROFILE_TYPE = "profile_type"
    }
}

