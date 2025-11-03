package com.github.wakingrufus.htmx

enum class HtmxEvent(val jsForm: String, val htmlForm: String = jsForm) {
    /**
     * send this event to an element to abort a request
     */
    ABORT("htmx:abort"),

    /**
     * triggered after an AJAX request has completed processing a successful response
     */
    AFTER_ON_LOAD("htmx:afterOnLoad", "htmx:after-on-load"),

    /**
     * triggered after htmx has initialized a node
     */
    AFTER_PROCESS_NODE("htmx:afterProcessNode", "htmx:after-process-node"),

    /**
     * triggered after an AJAX request has completed
     */
    AFTER_REQUEST("htmx:afterRequest", "htmx:after-request"),

    /**
     * triggered after the DOM has settled
     */
    AFTER_SETTLE("htmx:afterSettle", "htmx:after-settle"),

    /**
     * triggered after new content has been swapped in
     */
    AFTER_SWAP("htmx:afterSwap", "htmx:after-swap"),

    /**
     * triggered before htmx disables an element or removes it from the DOM
     */
    BEFORE_CLEANUP_ELEMENT("htmx:beforeCleanupElement", "htmx:before-cleanup-element"),

    /**
     * triggered before any response processing occurs
     */
    BEFORE_ON_LOAD("htmx:beforeOnLoad", "htmx:before-on-load"),

    /**
     * triggered before htmx initializes a node
     */
    BEFORE_PROCESS_NODE("htmx:beforeProcessNode", "htmx:before-process-node"),

    /**
     * triggered before an AJAX request is made
     */
    BEFORE_REQUEST("htmx:beforeRequest", "htmx:before-request"),

    /**
     * triggered before a swap is done, allows you to configure the swap
     */
    BEFORE_SWAP("htmx:beforeSwap", "htmx:before-swap"),

    /**
     * triggered just before an ajax request is sent
     */
    BEFORE_SEND("htmx:beforeSend", "htmx:before-send"),

    /**
     * triggered before the View Transition wrapped swap occurs
     */
    BEFORE_TRANSITION("htmx:beforeTransition", "htmx:before-transition"),

    /**
     * triggered before the request, allows you to customize parameters, headers
     */
    CONFIG_REQUEST("htmx:configRequest", "htmx:config-request"),

    /**
     * triggered after a trigger occurs on an element, allows you to cancel (or delay) issuing the AJAX request
     */
    CONFIRM("htmx:confirm"),

    /**
     * triggered on an error during cache writing
     */
    HISTORY_CACHE_ERROR("htmx:historyCacheError", "htmx:history-cache-error"),

    /**
     * triggered on a cache hit in the history subsystem
     */
    HISTORY_CACHE_HIT("htmx:historyCacheHit", "htmx:history-cache-hit"),

    /**
     * triggered on a cache miss in the history subsystem
     */
    HISTORY_CACHE_MISS("htmx:historyCacheMiss", "htmx:history-cache-miss"),

    /**
     * triggered on a unsuccessful remote retrieval
     */
    HISTORY_CACHE_MISS_LOAD_ERROR("htmx:historyCacheMissLoadError", "htmx:history-cache-miss-load-error"),

    /**
     * triggered on a successful remote retrieval
     */
    HISTORY_CACHE_MISS_LOAD("htmx:historyCacheMissLoad", "htmx:history-cache-miss-load"),

    /**
     * triggered when htmx handles a history restoration action
     */
    HISTORY_RESTORE("htmx:historyRestore", "htmx:history-restore"),

    /**
     * triggered before content is saved to the history cache
     */
    BEFORE_HISTORY_SAVE("htmx:beforeHistorySave", "htmx:before-history-save"),

    /**
     * triggered when new content is added to the DOM
     */
    LOAD("htmx:load"),

    /**
     * triggered when an element refers to a SSE event in its trigger, but no parent SSE source has been defined
     */
    NO_SSE_SOURCE_ERROR("htmx:noSSESourceError", "htmx:no-sse-source-error"),

    /**
     * triggered when an exception occurs during the onLoad handling in htmx
     */
    ON_LOAD_ERROR("htmx:onLoadError", "htmx:on-load-error"),

    /**
     * triggered after an out of band element as been swapped in
     */
    OOB_AFTER_SWAP("htmx:oobAfterSwap", "htmx:oob-after-swap"),

    /**
     * triggered before an out of band element swap is done, allows you to configure the swap
     */
    OOB_BEFORE_SWAP("htmx:oobBeforeSwap", "htmx:oob-before-swap"),

    /**
     * triggered when an out of band element does not have a matching ID in the current DOM
     */
    OOB_ERROR_NO_TARGET("htmx:oobErrorNoTarget", "htmx:oob-error-no-target"),

    /**
     * triggered after a prompt is shown
     */
    PROMPT("htmx:prompt"),

    /**
     * triggered after a url is pushed into history
     */
    PUSHED_INTO_HISTORY("htmx:pushedIntoHistory", "htmx:pushed-into-history"),

    /**
     * triggered after a url is replaced in history
     */
    REPLACED_IN_HISTORY("htmx:replacedInHistory", "htmx:replaced-in-history"),

    /**
     * triggered when an HTTP response error (non-200 or 300 response code) occurs
     */
    RESPONSE_ERROR("htmx:responseError", "htmx:response-error"),

    /**
     * triggered when a request is aborted
     */
    SEND_ABORT("htmx:sendAbort", "htmx:send-abort"),

    /**
     * triggered when a network error prevents an HTTP request from happening
     */
    SEND_ERROR("htmx:sendError", "htmx:send-error"),

    /**
     * triggered when an error occurs with a SSE source
     */
    SSE_ERROR("htmx:sseError", "htmx:sse-error"),

    /**
     * triggered when a SSE source is opened
     */
    SSE_OPEN("htmx:sseOpen", "htmx:sse-open"),

    /**
     * triggered when an error occurs during the swap phase
     */
    SWAP_ERROR("htmx:swapError", "htmx:swap-error"),

    /**
     * triggered when an invalid target is specified
     */
    TARGET_ERROR("htmx:targetError", "htmx:target-error"),

    /**
     * triggered when a request timeout occurs
     */
    TIMEOUT("htmx:timeout"),

    /**
     * triggered before an element is validated
     */
    VALIDATION_VALIDATE("htmx:validation:validate"),

    /**
     * triggered when an element fails validation
     */
    VALIDATION_FAILED("htmx:validation:failed"),

    /**
     * triggered when a request is halted due to validation errors
     */
    VALIDATION_HALTED("htmx:validation:halted"),

    /**
     * triggered when an ajax request aborts
     */
    XHR_ABORT("htmx:xhr:abort"),

    /**
     * triggered when an ajax request ends
     */
    XHR_LOADEND("htmx:xhr:loadend"),

    /**
     * triggered when an ajax request starts
     */
    XHR_LOADSTART("htmx:xhr:loadstart"),

    /**
     * triggered periodically during an ajax request that supports progress events
     */
    PROGRESS("htmx:xhr:progress")
}
