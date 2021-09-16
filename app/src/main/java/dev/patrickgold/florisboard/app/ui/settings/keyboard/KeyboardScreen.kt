/*
 * Copyright (C) 2021 Patrick Goldinger
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

package dev.patrickgold.florisboard.app.ui.settings.keyboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.patrickgold.florisboard.R
import dev.patrickgold.florisboard.app.ui.components.FlorisScreen
import dev.patrickgold.florisboard.ime.landscapeinput.LandscapeInputUiMode
import dev.patrickgold.florisboard.ime.onehanded.OneHandedMode
import dev.patrickgold.florisboard.ime.text.key.KeyHintMode
import dev.patrickgold.florisboard.ime.text.key.UtilityKeyAction
import dev.patrickgold.jetpref.ui.compose.DialogSliderPreference
import dev.patrickgold.jetpref.ui.compose.ListPreference
import dev.patrickgold.jetpref.ui.compose.Preference
import dev.patrickgold.jetpref.ui.compose.PreferenceGroup
import dev.patrickgold.jetpref.ui.compose.SwitchPreference
import dev.patrickgold.jetpref.ui.compose.annotations.ExperimentalJetPrefUi

@OptIn(ExperimentalJetPrefUi::class)
@Composable
fun KeyboardScreen() = FlorisScreen(title = stringResource(R.string.settings__keyboard__title)) {
    SwitchPreference(
        prefs.keyboard.numberRow,
        title = stringResource(R.string.pref__keyboard__number_row__label),
        summary = stringResource(R.string.pref__keyboard__number_row__summary),
    )
    ListPreference(
        listPref = prefs.keyboard.hintedNumberRowMode,
        switchPref = prefs.keyboard.hintedNumberRowEnabled,
        title = stringResource(R.string.pref__keyboard__hinted_number_row_mode__label),
        summarySwitchDisabled = stringResource(R.string.state__disabled),
        entries = KeyHintMode.listEntries(),
    )
    ListPreference(
        listPref = prefs.keyboard.hintedSymbolsMode,
        switchPref = prefs.keyboard.hintedSymbolsEnabled,
        title = stringResource(R.string.pref__keyboard__hinted_symbols_mode__label),
        summarySwitchDisabled = stringResource(R.string.state__disabled),
        entries = KeyHintMode.listEntries(),
    )
    SwitchPreference(
        prefs.keyboard.utilityKeyEnabled,
        title = stringResource(R.string.pref__keyboard__utility_key_enabled__label),
        summary = stringResource(R.string.pref__keyboard__utility_key_enabled__summary),
    )
    ListPreference(
        prefs.keyboard.utilityKeyAction,
        title = stringResource(R.string.pref__keyboard__utility_key_action__label),
        entries = UtilityKeyAction.listEntries(),
        visibleIf = { prefs.keyboard.utilityKeyEnabled isEqualTo true },
    )
    DialogSliderPreference(
        primaryPref = prefs.keyboard.fontSizeMultiplierPortrait,
        secondaryPref = prefs.keyboard.fontSizeMultiplierLandscape,
        title = stringResource(R.string.pref__keyboard__font_size_multiplier__label),
        primaryLabel = stringResource(R.string.screen_orientation__portrait),
        secondaryLabel = stringResource(R.string.screen_orientation__landscape),
        unit = stringResource(R.string.unit__percent__symbol),
        min = 50,
        max = 150,
        stepIncrement = 5,
    )

    PreferenceGroup(title = stringResource(R.string.pref__keyboard__group_layout__label)) {
        ListPreference(
            prefs.keyboard.oneHandedMode,
            title = stringResource(R.string.pref__keyboard__one_handed_mode__label),
            entries = OneHandedMode.listEntries(),
        )
        DialogSliderPreference(
            prefs.keyboard.oneHandedModeScaleFactor,
            title = stringResource(R.string.pref__keyboard__one_handed_mode_scale_factor__label),
            unit = stringResource(R.string.unit__percent__symbol),
            min = 70,
            max = 90,
            stepIncrement = 1,
            enabledIf = { prefs.keyboard.oneHandedMode isNotEqualTo OneHandedMode.OFF },
        )
        ListPreference(
            prefs.keyboard.landscapeInputUiMode,
            title = stringResource(R.string.pref__keyboard__landscape_input_ui_mode__label),
            entries = LandscapeInputUiMode.listEntries(),
        )
        DialogSliderPreference(
            prefs.keyboard.heightFactor,
            title = stringResource(R.string.pref__keyboard__height_factor__label),
            unit = stringResource(R.string.unit__percent__symbol),
            min = 50,
            max = 150,
            stepIncrement = 5,
        )
        DialogSliderPreference(
            primaryPref = prefs.keyboard.keySpacingVertical,
            secondaryPref = prefs.keyboard.keySpacingHorizontal,
            title = stringResource(R.string.pref__keyboard__key_spacing__label),
            primaryLabel = stringResource(R.string.screen_orientation__vertical),
            secondaryLabel = stringResource(R.string.screen_orientation__horizontal),
            unit = stringResource(R.string.unit__display_pixel__symbol),
            min = 0.0f,
            max = 10.0f,
            stepIncrement = 0.5f,
        )
        DialogSliderPreference(
            primaryPref = prefs.keyboard.bottomOffsetPortrait,
            secondaryPref = prefs.keyboard.bottomOffsetLandscape,
            title = stringResource(R.string.pref__keyboard__bottom_offset__label),
            primaryLabel = stringResource(R.string.screen_orientation__vertical),
            secondaryLabel = stringResource(R.string.screen_orientation__horizontal),
            unit = stringResource(R.string.unit__display_pixel__symbol),
            min = 0,
            max = 60,
            stepIncrement = 1,
        )
    }

    PreferenceGroup(title = stringResource(R.string.pref__keyboard__group_keypress__label)) {
        Preference(
            title = stringResource(R.string.settings__input_feedback__title),
            onClick = { /* TODO */ },
        )
        SwitchPreference(
            prefs.keyboard.popupEnabled,
            title = stringResource(R.string.pref__keyboard__popup_enabled__label),
            summary = stringResource(R.string.pref__keyboard__popup_enabled__summary),
        )
        SwitchPreference(
            prefs.keyboard.mergeHintPopupsEnabled,
            title = stringResource(R.string.pref__keyboard__merge_hint_popups_enabled__label),
            summary = stringResource(R.string.pref__keyboard__merge_hint_popups_enabled__summary),
        )
        DialogSliderPreference(
            prefs.keyboard.longPressDelay,
            title = stringResource(R.string.pref__keyboard__long_press_delay__label),
            unit = stringResource(R.string.unit__milliseconds__symbol),
            min = 100,
            max = 700,
            stepIncrement = 10,
        )
        SwitchPreference(
            prefs.keyboard.spaceBarSwitchesToCharacters,
            title = stringResource(R.string.pref__keyboard__space_bar_switches_to_characters__label),
            summary = stringResource(R.string.pref__keyboard__space_bar_switches_to_characters__summary),
        )
    }
}
