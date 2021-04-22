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

package dev.patrickgold.florisboard.ime.text.key

import dev.patrickgold.florisboard.ime.popup.PopupSet
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
sealed class KeyData {
    abstract fun compute(
        caps: Boolean = false,
        variation: KeyVariation = KeyVariation.ALL
    ): KeyData

    abstract fun asString(
        isForDisplay: Boolean
    ): String
}

/**
 * Data class which describes a single key and its attributes.
 *
 * @property type The type of the key. Some actions require both [code] and [type] to match in order
 *  to be successfully executed. Defaults to [KeyType.CHARACTER].
 * @property code The UTF-8 encoded code of the character. The code defined here is used as the
 *  data passed to the system. Defaults to 0.
 * @property asString The string used to display the key in the UI. Is not used for the actual data
 *  passed to the system. Should normally be the exact same as the [code]. Defaults to an empty
 *  string.
 */
@Serializable
@SerialName("text_key")
class TextKeyData(
    val type: KeyType = KeyType.CHARACTER,
    val code: Int = 0,
    val label: String = ""
) : KeyData() {
    companion object {
        fun obtain(other: TextKeyData): TextKeyData = TextKeyData(
            type = other.type,
            code = other.code,
            label = other.label
        )

        fun obtain(other: PopupAwareTextKeyData): TextKeyData = TextKeyData(
            type = other.type,
            code = other.code,
            label = other.label
        )

        /** Predefined key data for [KeyCode.ARROW_DOWN] */
        val ARROW_DOWN = TextKeyData(
            type = KeyType.NAVIGATION,
            code = KeyCode.ARROW_DOWN,
            label = "arrow_down"
        )

        /** Predefined key data for [KeyCode.ARROW_LEFT] */
        val ARROW_LEFT = TextKeyData(
            type = KeyType.NAVIGATION,
            code = KeyCode.ARROW_LEFT,
            label = "arrow_left"
        )

        /** Predefined key data for [KeyCode.ARROW_RIGHT] */
        val ARROW_RIGHT = TextKeyData(
            type = KeyType.NAVIGATION,
            code = KeyCode.ARROW_RIGHT,
            label = "arrow_right"
        )

        /** Predefined key data for [KeyCode.ARROW_UP] */
        val ARROW_UP = TextKeyData(
            type = KeyType.NAVIGATION,
            code = KeyCode.ARROW_UP,
            label = "arrow_up"
        )

        /** Predefined key data for [KeyCode.CLIPBOARD_COPY] */
        val CLIPBOARD_COPY = TextKeyData(
            type = KeyType.SYSTEM_GUI,
            code = KeyCode.CLIPBOARD_COPY,
            label = "clipboard_copy"
        )

        /** Predefined key data for [KeyCode.CLIPBOARD_CUT] */
        val CLIPBOARD_CUT = TextKeyData(
            type = KeyType.SYSTEM_GUI,
            code = KeyCode.CLIPBOARD_CUT,
            label = "clipboard_cut"
        )

        /** Predefined key data for [KeyCode.CLIPBOARD_PASTE] */
        val CLIPBOARD_PASTE = TextKeyData(
            type = KeyType.SYSTEM_GUI,
            code = KeyCode.CLIPBOARD_PASTE,
            label = "clipboard_paste"
        )

        /** Predefined key data for [KeyCode.CLIPBOARD_SELECT] */
        val CLIPBOARD_SELECT = TextKeyData(
            type = KeyType.SYSTEM_GUI,
            code = KeyCode.CLIPBOARD_SELECT,
            label = "clipboard_select"
        )

        /** Predefined key data for [KeyCode.CLIPBOARD_SELECT_ALL] */
        val CLIPBOARD_SELECT_ALL = TextKeyData(
            type = KeyType.SYSTEM_GUI,
            code = KeyCode.CLIPBOARD_SELECT_ALL,
            label = "clipboard_select_all"
        )

        /** Predefined key data for [KeyCode.DELETE] */
        val DELETE = TextKeyData(
            type = KeyType.ENTER_EDITING,
            code = KeyCode.DELETE,
            label = "delete"
        )

        /** Predefined key data for [KeyCode.DELETE_WORD] */
        val DELETE_WORD = TextKeyData(
            type = KeyType.ENTER_EDITING,
            code = KeyCode.DELETE_WORD,
            label = "delete_word"
        )

        /** Predefined key data for [KeyCode.INTERNAL_BATCH_EDIT] */
        val INTERNAL_BATCH_EDIT = TextKeyData(
            type = KeyType.FUNCTION,
            code = KeyCode.INTERNAL_BATCH_EDIT,
            label = "internal_batch_edit"
        )

        /** Predefined key data for [KeyCode.MOVE_START_OF_LINE] */
        val MOVE_START_OF_LINE = TextKeyData(
            type = KeyType.NAVIGATION,
            code = KeyCode.MOVE_START_OF_LINE,
            label = "move_start_of_line"
        )

        /** Predefined key data for [KeyCode.MOVE_END_OF_LINE] */
        val MOVE_END_OF_LINE = TextKeyData(
            type = KeyType.NAVIGATION,
            code = KeyCode.MOVE_END_OF_LINE,
            label = "move_end_of_line"
        )

        /** Predefined key data for [KeyCode.MOVE_START_OF_PAGE] */
        val MOVE_START_OF_PAGE = TextKeyData(
            type = KeyType.NAVIGATION,
            code = KeyCode.MOVE_START_OF_PAGE,
            label = "move_start_of_page"
        )

        /** Predefined key data for [KeyCode.MOVE_END_OF_PAGE] */
        val MOVE_END_OF_PAGE = TextKeyData(
            type = KeyType.NAVIGATION,
            code = KeyCode.MOVE_END_OF_PAGE,
            label = "move_end_of_page"
        )

        /** Predefined key data for [KeyCode.REDO] */
        val REDO = TextKeyData(
            type = KeyType.SYSTEM_GUI,
            code = KeyCode.REDO,
            label = "redo"
        )

        /** Predefined key data for [KeyCode.SHOW_INPUT_METHOD_PICKER] */
        val SHOW_INPUT_METHOD_PICKER = TextKeyData(
            type = KeyType.FUNCTION,
            code = KeyCode.SHOW_INPUT_METHOD_PICKER,
            label = "show_input_method_picker"
        )

        /** Predefined key data for [KeyCode.SWITCH_TO_TEXT_CONTEXT] */
        val SWITCH_TO_TEXT_CONTEXT = TextKeyData(
            type = KeyType.SYSTEM_GUI,
            code = KeyCode.SWITCH_TO_TEXT_CONTEXT,
            label = "switch_to_text_context"
        )
        /** Predefined key data for [KeyCode.SWITCH_TO_CLIPBOARD_CONTEXT] */
        val SWITCH_TO_CLIPBOARD_CONTEXT = TextKeyData(
            type = KeyType.SYSTEM_GUI,
            code = KeyCode.SWITCH_TO_CLIPBOARD_CONTEXT,
            label = "switch_to_clipboard_context"
        )

        /** Predefined key data for [KeyCode.SHIFT] */
        val SHIFT = TextKeyData(
            type = KeyType.MODIFIER,
            code = KeyCode.SHIFT,
            label = "shift"
        )

        /** Predefined key data for [KeyCode.SHIFT_LOCK] */
        val SHIFT_LOCK = TextKeyData(
            type = KeyType.MODIFIER,
            code = KeyCode.SHIFT_LOCK,
            label = "shift_lock"
        )

        /** Predefined key data for [KeyCode.SPACE] */
        val SPACE = TextKeyData(
            type = KeyType.CHARACTER,
            code = KeyCode.SPACE,
            label = "space"
        )

        /** Predefined key data for [KeyCode.UNDO] */
        val UNDO = TextKeyData(
            type = KeyType.SYSTEM_GUI,
            code = KeyCode.UNDO,
            label = "undo"
        )

        /** Predefined key data for [KeyCode.UNSPECIFIED] */
        val UNSPECIFIED = TextKeyData(
            type = KeyType.UNSPECIFIED,
            code = KeyCode.UNSPECIFIED,
            label = "unspecified"
        )

        /** Predefined key data for [KeyCode.VIEW_CHARACTERS] */
        val VIEW_CHARACTERS = TextKeyData(
            type = KeyType.SYSTEM_GUI,
            code = KeyCode.VIEW_CHARACTERS,
            label = "view_characters"
        )

        /** Predefined key data for [KeyCode.VIEW_SYMBOLS] */
        val VIEW_SYMBOLS = TextKeyData(
            type = KeyType.SYSTEM_GUI,
            code = KeyCode.VIEW_SYMBOLS,
            label = "view_symbols"
        )

        /** Predefined key data for [KeyCode.VIEW_SYMBOLS2] */
        val VIEW_SYMBOLS2 = TextKeyData(
            type = KeyType.SYSTEM_GUI,
            code = KeyCode.VIEW_SYMBOLS2,
            label = "view_symbols2"
        )

        /** Predefined key data for [KeyCode.VIEW_NUMERIC_ADVANCED] */
        val VIEW_NUMERIC_ADVANCED = TextKeyData(
            type = KeyType.SYSTEM_GUI,
            code = KeyCode.VIEW_NUMERIC_ADVANCED,
            label = "view_numeric_advanced"
        )
    }

    override fun compute(caps: Boolean, variation: KeyVariation): TextKeyData {
        return if (caps && code >= KeyCode.SPACE) {
            val upperCaseChar = code.toChar().toUpperCase()
            TextKeyData(type, upperCaseChar.toInt(), upperCaseChar.toString())
        } else {
            this
        }
    }

    override fun asString(isForDisplay: Boolean): String {
        return StringBuilder().run {
            // Combining Diacritical Marks
            // See: https://en.wikipedia.org/wiki/Combining_Diacritical_Marks
            if (isForDisplay && code in 0x0300..0x036F) {
                append("◌")
            }
            append(label)
            toString()
        }
    }

    override fun toString(): String {
        return "TextKeyData { type=$type code=$code label=\"$label\" }"
    }
}

@Serializable
@SerialName("popup_aware_text_key")
class PopupAwareTextKeyData(
    val type: KeyType = KeyType.CHARACTER,
    val code: Int = 0,
    val label: String = "",
    val groupId: Int = GROUP_DEFAULT,
    val popup: PopupSet<TextKeyData> = PopupSet()
) : KeyData() {
    companion object {
        /**
         * Constant for the default group. If not otherwise specified, any key is automatically
         * assigned to this group.
         */
        const val GROUP_DEFAULT: Int = 0

        /**
         * Constant for the Left modifier key group. Any key belonging to this group will get the
         * popups specified for "~left" in the popup mapping.
         */
        const val GROUP_LEFT: Int = 1

        /**
         * Constant for the right modifier key group. Any key belonging to this group will get the
         * popups specified for "~right" in the popup mapping.
         */
        const val GROUP_RIGHT: Int = 2

        /**
         * Constant for the enter modifier key group. Any key belonging to this group will get the
         * popups specified for "~enter" in the popup mapping.
         */
        const val GROUP_ENTER: Int = 3
    }

    override fun compute(caps: Boolean, variation: KeyVariation): PopupAwareTextKeyData {
        return if (caps && code >= KeyCode.SPACE) {
            val upperCaseChar = code.toChar().toUpperCase()
            PopupAwareTextKeyData(type, upperCaseChar.toInt(), upperCaseChar.toString(), groupId, popup)
        } else {
            this
        }
    }

    override fun asString(isForDisplay: Boolean): String {
        return StringBuilder().run {
            // Combining Diacritical Marks
            // See: https://en.wikipedia.org/wiki/Combining_Diacritical_Marks
            if (isForDisplay && code in 0x0300..0x036F) {
                append("◌")
            }
            append(label)
            toString()
        }
    }

    override fun toString(): String {
        return "PopupAwareTextKeyData { type=$type code=$code label=\"$label\" popup=$popup }"
    }
}

/**
 * Data class for a single emoji (with possible emoji variants in [popup]).
 *
 * @property codePoints The code points of the emoji.
 * @property asString The name of the emoji.
 * @property popup List of possible variants of the emoji.
 */
@Serializable
@SerialName("emoji_key")
class EmojiKeyData(
    val codePoints: List<Int>,
    val label: String = "",
    val popup: MutableList<EmojiKeyData> = mutableListOf()
) : KeyData() {
    override fun compute(caps: Boolean, variation: KeyVariation): EmojiKeyData {
        return this
    }

    override fun asString(isForDisplay: Boolean): String {
        var ret = ""
        for (codePoint in codePoints) {
            ret += String(Character.toChars(codePoint))
        }
        return ret
    }

    override fun toString(): String {
        return "EmojiKeyData"// { code=$code label=\"$label\" }"
    }
}

@Serializable
@SerialName("case_selector")
class CaseSelector(
    val lower: KeyData,
    val upper: KeyData
) : KeyData() {
    override fun compute(caps: Boolean, variation: KeyVariation): KeyData {
        return (if (caps) { upper } else { lower }).compute(caps, variation)
    }

    override fun asString(isForDisplay: Boolean): String {
        return ""
    }
}

@Serializable
@SerialName("variation_selector")
data class VariationSelector(
    val default: KeyData,
    val email: KeyData? = null,
    val normal: KeyData? = null,
    val password: KeyData? = null,
    val uri: KeyData? = null,
) : KeyData() {
    override fun compute(caps: Boolean, variation: KeyVariation): KeyData {
        return when (variation) {
            KeyVariation.ALL -> default
            KeyVariation.EMAIL_ADDRESS -> email ?: default
            KeyVariation.NORMAL -> normal ?: default
            KeyVariation.PASSWORD -> password ?: default
            KeyVariation.URI -> uri ?: default
        }.compute(caps, variation)
    }

    override fun asString(isForDisplay: Boolean): String {
        return ""
    }
}
