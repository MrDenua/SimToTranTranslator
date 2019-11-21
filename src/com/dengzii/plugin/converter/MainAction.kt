package com.dengzii.plugin.converter

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys

/**
 * <pre>
 * author : dengzi
 * e-mail : denua@foxmail.com
 * github : https://github.com/MrDenua
 * time   : 2019/11/21
 * desc   :
 * </pre>
 */
class MainAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {

        val editor = e.getData(PlatformDataKeys.EDITOR)
        val selectedText: String?
        editor?.let {
            selectedText = it.selectionModel.selectedText
            if (selectedText != null) {
                val start = it.selectionModel.selectionStart
                val end = it.selectionModel.selectionEnd
                val text = editor.document.text.replaceRange(
                        start..end + 1, ConvertUtils.simToTra(selectedText))
                editor.document.setText(text)
                return
            }
        }
        ConverterDialog().create()
    }
}