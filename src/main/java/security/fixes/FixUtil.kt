package security.fixes

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import com.jetbrains.python.psi.LanguageLevel
import com.jetbrains.python.psi.PyCallExpression
import com.jetbrains.python.psi.PyElementGenerator

fun getCallElementAtCaret(file: PsiFile, editor: Editor): PyCallExpression? {
    val call = PsiTreeUtil.getParentOfType(file.findElementAt(editor.caretModel.offset), PyCallExpression::class.java) ?: return null
    return call
}

fun getNewCallExpressiontAtCaret(file: PsiFile, editor: Editor, project: Project, old: String, new: String): PyCallExpression ? {
    var callElement = getCallElementAtCaret(file, editor) ?: return null
    val elementGenerator = PyElementGenerator.getInstance(project)
    val newEl = elementGenerator.createExpressionFromText(LanguageLevel.getDefault(), callElement.text.replace(old, new)) as PyCallExpression
    return newEl
}

