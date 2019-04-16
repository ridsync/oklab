package com.okitoki.okchat.util

import java.util.concurrent.Executors

/**
 * @author ridsync
 */
private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

fun ioThread(f : () -> Unit) = IO_EXECUTOR.execute(f)