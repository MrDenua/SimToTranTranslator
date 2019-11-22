package com.dengzii.plugin.converter

import com.intellij.psi.xml.XmlText
import com.intellij.util.ThrowableRunnable

/**
 * <pre>
 * author : dengzi
 * e-mail : denua@foxmail.com
 * github : https://github.com/MrDenua
 * time   : 2019/11/22
 * desc   :
 * </pre>
 */
class TextWriter(private val node: XmlText) : ThrowableRunnable<RuntimeException> {

    override fun run() {
        node.value = ConvertUtils.simToTra(node.value)
    }
}