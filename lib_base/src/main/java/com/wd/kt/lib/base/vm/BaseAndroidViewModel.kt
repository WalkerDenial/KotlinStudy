package com.wd.kt.lib.base.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 2020/11/8 00:22.
 */
abstract class BaseAndroidViewModel<T : Application>(application: T) : AndroidViewModel(application)