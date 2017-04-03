/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.izeye.util;

/**
 * Utilities for {@link Exception}.
 *
 * @author Johnny Lim
 */
public final class ExceptionUtils {

    private ExceptionUtils() {
    }

    public static boolean hasCause(Throwable ex, Class<? extends Throwable> causeClass) {
        Throwable cause = ex;
        while (cause != null) {
            if (causeClass.isInstance(cause)) {
                return true;
            }
            cause = cause.getCause();
        }
        return false;
    }

}
