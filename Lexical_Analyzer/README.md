<b>CS 316 : Principles of Progrmaming Languages </b>
<hr>

Project 1: Lexical Analyzer 
<hr>
<b>EBNF:</b> <br>
⟨digit⟩ → 0 | 1 | ... | 9<br>
⟨unsigned int⟩ → {⟨digit⟩}+<br>
⟨signed int⟩ → (+|−) {⟨digit⟩}+<br>
⟨float⟩ → [+|−] ( {⟨digit⟩}+ "." {⟨digit⟩} | "." {⟨digit⟩}+ )<br>
⟨floatE⟩ → ⟨float⟩ (e|E) [+|−] {⟨digit⟩}+<br>
⟨instruction name⟩ → "iconst" | "iload" | "istore" | "fconst" | "fload" | "fstore" |<br>
     "iadd" | "isub" | "imul" | "idiv" | "fadd" | "fsub" | "fmul" | "fdiv" |
     <br>"intToFloat" |
     <br> "icmpeq" | "icmpne" | "icmplt" | "icmple" | "icmpgt" | "icmpge" |
	  <br>"fcmpeq" | "fcmpne" | "fcmplt" | "fcmple" | "fcmpgt" | "fcmpge" |
       <br>"goto" | "invoke" | "return" | "ireturn" | "freturn" | "print"<br>
⟨colon⟩ → ":"<br>
⟨comma⟩ → ","<br><br>


<b>DFA:</b>
<img src="https://github.com/isaac-ba/Lexical_Analyzer/blob/master/DFA.png">
<br><br>
<hr>

# Java
