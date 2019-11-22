package com.dengzii.plugin.converter

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.psi.xml.XmlText
import com.intellij.psi.xml.XmlToken

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
        val psiFile = e.getData(CommonDataKeys.PSI_FILE)
        editor?.let {
            val xmlTokenText = psiFile?.findElementAt(editor.caretModel.offset) ?: return@let

            if (xmlTokenText is XmlToken) {
                val xmlText = xmlTokenText.parent as XmlText
                WriteCommandAction.writeCommandAction(e.project).run(TextWriter(xmlText))
                return
            }
        }

        ConverterDialog().create()
    }
}