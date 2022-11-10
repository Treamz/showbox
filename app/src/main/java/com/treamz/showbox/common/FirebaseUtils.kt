package com.treamz.showbox.common

import kotlinx.coroutines.ExperimentalCoroutinesApi
//
//@OptIn(ExperimentalCoroutinesApi::class)
//suspend fun <T> Task<T>.await(): T {
//    return suspendCancellableCoroutine { cont ->
//        addOnCompleteListener {
//            if (it.exception != null) {
//                cont.resumeWithException(it.exception!!)
//            } else {
//                cont.resume(it.result, null)
//            }
//        }
//    }
//}