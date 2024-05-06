package com.myaxa.search_impl.models

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.myaxa.search_impl.R
import com.myaxa.common.R as CommonR

sealed class HintButtonInfo(
    @ColorRes val colorRes: Int,
    @StringRes val titleId: Int,
    @DrawableRes val iconId: Int,
    val clickListener: (test: String) -> Unit,
) {
    data class RouteButtonInfo(
        val listener: (test: String) -> Unit,
    ) : HintButtonInfo(
        colorRes = CommonR.color.dark_green,
        titleId = R.string.route_button,
        iconId = CommonR.drawable.ic_route,
        clickListener = listener,
    )

    data class GlobeButtonInfo(
        val listener: (test: String) -> Unit,
    ) : HintButtonInfo(
        colorRes = CommonR.color.blue,
        titleId = R.string.globe_button,
        iconId = CommonR.drawable.ic_globe,
        clickListener = listener,
    )

    data class WeekendsButtonInfo(
        val listener: (test: String) -> Unit,
    ) : HintButtonInfo(
        colorRes = CommonR.color.dark_blue,
        titleId = R.string.weekends_button,
        iconId = CommonR.drawable.ic_calendar,
        clickListener = listener,
    )

    data class HotTicketsButtonInfo(
        val listener: (test: String) -> Unit,
    ) : HintButtonInfo(
        colorRes = CommonR.color.red,
        titleId = R.string.hot_tickets_button,
        iconId = CommonR.drawable.ic_flame,
        clickListener = listener,
    )
}