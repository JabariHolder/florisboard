/*
 * Copyright (C) 2020 Patrick Goldinger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.patrickgold.florisboard.ime.text.key

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.patrickgold.florisboard.R
import dev.patrickgold.jetpref.datastore.model.PreferenceSerializer
import dev.patrickgold.jetpref.ui.compose.entry

/**
 * Enum for the key hint modes.
 */
enum class KeyHintMode {
    DISABLED,
    HINT_PRIORITY,
    ACCENT_PRIORITY,
    SMART_PRIORITY;

    companion object {
        fun fromString(string: String): KeyHintMode {
            return valueOf(string.uppercase())
        }

        @Composable
        fun listEntries() = listOf(
            entry(
                key = ACCENT_PRIORITY,
                label = stringResource(R.string.enum__key_hint_mode__accent_priority),
            ),
            entry(
                key = HINT_PRIORITY,
                label = stringResource(R.string.enum__key_hint_mode__hint_priority),
            ),
            entry(
                key = SMART_PRIORITY,
                label = stringResource(R.string.enum__key_hint_mode__smart_priority),
            ),
        )
    }

    override fun toString(): String {
        return super.toString().lowercase()
    }

    object Serializer : PreferenceSerializer<KeyHintMode> {
        override fun serialize(value: KeyHintMode): String {
            return value.toString()
        }

        override fun deserialize(value: String): KeyHintMode? {
            return try {
                fromString(value)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }
}
