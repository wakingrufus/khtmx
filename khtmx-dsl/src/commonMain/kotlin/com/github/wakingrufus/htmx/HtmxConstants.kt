package com.github.wakingrufus.htmx

/**
 * Classes used by [HTMX](https://htmx.org/reference/#classes)
 */
object CssClass {
    /**
     * Applied to a new piece of content before it is swapped, removed after it is settled.
     */
    val htmxAdded = "htmx-added"

    /**
     * 	A dynamically generated class that will toggle visible (opacity:1) when a htmx-request class is present
     */
    val htmxIndicator = "htmx-indicator"

    /**
     * 	Applied to either the element or the element specified with hx-indicator while a request is ongoing
     */
    val htmxRequest = "htmx-request"

    /**
     * Applied to a target after content is swapped, removed after it is settled. The duration can be modified via hx-swap.
     */
    val htmxSettling = "htmx-settling"

    /**
     * Applied to a target before any content is swapped, removed after it is swapped. The duration can be modified via hx-swap.
     */
    val htmxSwapping = "htmx-swapping"
}

/**
 * Headers used in requests sent by [HTMX](https://htmx.org/reference/#request_headers)
 */
object RequestHeader {
    /**
     * indicates that the request is via an element using hx-boost
     */
    val hxBoosted = "HX-Boosted"

    /**
     * the current URL of the browser
     *
     */
    val hxCurrentUrl = "HX-Current-URL"

    /**
     * “true” if the request is for history restoration after a miss in the local history cache
     */
    val hxHistoryRestoreRequest = "HX-History-Restore-Request"

    /**
     * the user response to an hx-prompt
     */
    val hxPrompt = "HX-Prompt"

    /**
     * always “true”
     */
    val hxRequest = "HX-Request"

    /**
     * the id of the target element if it exists
     */
    val hxTarget = "HX-Target"

    /**
     * the name of the triggered element if it exists
     */
    val hxTriggerName = "HX-Trigger-Name"

    /**
     * the id of the triggered element if it exists
     */
    val hxTrigger = "HX-Trigger"
}

/**
 * Headers interpreted by [HTMX](https://htmx.org/reference/#response_headers) when receiving responses from the server.
 */
object ResponseHeader {
    /**
     * allows you to do a client-side redirect that does not do a full page reload
     */
    val hxLocation = "HX-Location"

    /**
     * pushes a new url into the history stack
     */
    val hxPushUrl = "HX-Push-Url"

    /**
     * can be used to do a client-side redirect to a new location
     */
    val hxRedirect = "HX-Redirect"

    /**
     * if set to “true” the client-side will do a full refresh of the page
     */
    val hxRefresh = "HX-Refresh"

    /**
     * replaces the current URL in the location bar
     */
    val hxReplaceUrl = "HX-Replace-Url"

    /**
     * allows you to specify how the response will be swapped. See hx-swap for possible values
     */
    val hxReswap = "HX-Reswap"

    /**
     * a CSS selector that updates the target of the content update to a different element on the page
     */
    val hxRetarget = "HX-Retarget"

    /**
     * a CSS selector that allows you to choose which part of the response is used to be swapped in.
     * Overrides an existing hx-select on the triggering element
     */
    val hxReselect = "HX-Reselect"

    /**
     * allows you to trigger client-side events
     */
    val hxTrigger = "HX-Trigger"

    /**
     * allows you to trigger client-side events after the settle step
     */
    val hxTriggerAfterSettle = "HX-Trigger-After-Settle"

    /**
     * allows you to trigger client-side events after the swap step
     */
    val hxTriggerAfterSwap = "HX-Trigger-After-Swap"
}
