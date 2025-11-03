package com.github.wakingrufus.htmx

/**
 * provide syntactic sugar for ideomatic htmx event references.
 * Instead of
 * `hxOn(HtmxEvent.LOAD, "alert('Clicked!')")`
 * you can write
 * `hxOn(::load, "alert('Clicked!')")`
 */
object HtmxEvents {
    fun abort() = HtmxEvent.ABORT
    fun afterOnLoad() = HtmxEvent.AFTER_ON_LOAD
    fun afterProcessNode() = HtmxEvent.AFTER_PROCESS_NODE
    fun afterRequest() = HtmxEvent.AFTER_REQUEST
    fun afterSettle() = HtmxEvent.AFTER_SETTLE
    fun afterSwap() = HtmxEvent.AFTER_SWAP
    fun beforeCleanupElement() = HtmxEvent.BEFORE_CLEANUP_ELEMENT
    fun beforeOnLoad() = HtmxEvent.BEFORE_ON_LOAD
    fun beforeProcessNode() = HtmxEvent.BEFORE_PROCESS_NODE
    fun beforeRequest() = HtmxEvent.BEFORE_REQUEST
    fun beforeSwap() = HtmxEvent.BEFORE_SWAP
    fun beforeSend() = HtmxEvent.BEFORE_SEND
    fun beforeTransition() = HtmxEvent.BEFORE_TRANSITION
    fun configRequest() = HtmxEvent.CONFIG_REQUEST
    fun confirm() = HtmxEvent.CONFIRM
    fun historyCacheError() = HtmxEvent.HISTORY_CACHE_ERROR
    fun historyCacheHit() = HtmxEvent.HISTORY_CACHE_HIT
    fun historyCacheMiss() = HtmxEvent.HISTORY_CACHE_MISS
    fun historyCacheMissLoadError() = HtmxEvent.HISTORY_CACHE_MISS_LOAD_ERROR
    fun historyCacheMissLoad() = HtmxEvent.HISTORY_CACHE_MISS_LOAD
    fun historyRestore() = HtmxEvent.HISTORY_RESTORE
    fun beforeHistorySave() = HtmxEvent.BEFORE_HISTORY_SAVE
    fun load() = HtmxEvent.LOAD
    fun noSSESourceError() = HtmxEvent.NO_SSE_SOURCE_ERROR
    fun onLoadError() = HtmxEvent.ON_LOAD_ERROR
    fun oobAfterSwap() = HtmxEvent.OOB_AFTER_SWAP
    fun oobBeforeSwap() = HtmxEvent.OOB_BEFORE_SWAP
    fun oobErrorNoTarget() = HtmxEvent.OOB_ERROR_NO_TARGET
    fun prompt() = HtmxEvent.PROMPT
    fun pushedIntoHistory() = HtmxEvent.PUSHED_INTO_HISTORY
    fun replacedInHistory() = HtmxEvent.REPLACED_IN_HISTORY
    fun responseError() = HtmxEvent.RESPONSE_ERROR
    fun sendAbort() = HtmxEvent.SEND_ABORT
    fun sendError() = HtmxEvent.SEND_ERROR
    fun sseError() = HtmxEvent.SSE_ERROR
    fun sseOpen() = HtmxEvent.SSE_OPEN
    fun swapError() = HtmxEvent.SWAP_ERROR
    fun targetError() = HtmxEvent.TARGET_ERROR
    fun timeout() = HtmxEvent.TIMEOUT
}
