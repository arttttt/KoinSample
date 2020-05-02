package com.arttttt.koinsample.utils

inline fun<reified R> Any?.castTo(): R? {
    return this as? R
}