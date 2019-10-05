/*
 * Isaac Ben-Ami Project 1 : Lexical Analyzer written in JAVA
 * Code is based on provided code, given EBNF, & given DFA
 */
public class lex extends IO {

	public enum State { 

		// non-final states   ordinal number
		Start, add,	sub, period, E, EPlusMinus,    
		//final states
		Id,colon,comma,Float,floatE,unsignedInt,signedInt,

		//Identifiers   
		iconst, iload, istore, fconst, fload, fstore, iadd, isub, imul, idiv, fadd, fsub, fmul, fdiv, 
		intToFloat, 
		icmpeq, icmpne, icmplt, icmple, icmpgt, icmpge, fcmpeq, fcmpne, fcmplt, fcmple, fcmpgt, fcmpge, 
		Goto, invoke, Return, ireturn, freturn, print,       
		UNDEF;

		private boolean isFinal()
		{
			return ( this.compareTo(State.Id) >= 0 );  
		}

	}

	public static String t; // holds an extracted token
	public static State state; // the current state of the FA


	private static int driver(){
	
		State nextSt; 
		t = "";
		state = State.Start;

		if (Character.isWhitespace((char)a))// skip whitespace,new lines, carriage returns, etc.
			a = getChar(); 
		if ( a == -1 ) 
			return -1;

		while ( a != -1 ) 
		{
			c = (char) a;
			nextSt = nextState( state, c );
			if ( nextSt == State.UNDEF ) 
			{
				if ( state.isFinal() ) {
					if(state == State.Id) 
						state = getID(t);
					return 1; 
				}
				else 
				{
					t = t + c;
					a = getNextChar();
					return 0; 
				}
			}
			else 
			{
				state = nextSt;
				t = t + c;
				a = getNextChar();
			}
		}

		if ( state.isFinal() )
			return 1; 
		else
			return 0; 
	} 

	public static void getToken(){
		int i = driver();
		if ( i == 0 )
			displayln(t + " : Lexical Error, invalid token");
	}
	
	private static State nextState(State s, char c){

		switch (state) {
			case Start:
				if ( Character.isLetter(c) )
					return State.Id;
				else if (c == ':')
					return State.colon;
				else if(c == ',')
					return State.comma;
				else if(c == '+')
					return State.add;
				else if(c == '-')
					return State.sub;
				else if(c == '.')
					return State.period;
				else if(Character.isDigit(c))
					return State.unsignedInt;
				else
					return State.UNDEF;

			case Id:
				if ( Character.isLetter(c) )
						return State.Id;
				else 
					return State.UNDEF;

			case unsignedInt:
				if(Character.isDigit(c))
					return State.unsignedInt;
				else if(c == '.')
					return State.Float;
				else 
					return State.UNDEF;

			case add: 
			case sub:
				if(Character.isDigit(c))
					return State.signedInt;
				else if(c == '.')
					return State.period;
				else 
					return State.UNDEF;

			case period:
				if(Character.isDigit(c))
					return State.Float;
				else 
					return State.UNDEF;

			case signedInt:
				if(Character.isDigit(c))
					return State.signedInt;	
				else if(c == '.')
					return State.Float;
				else 
					return State.UNDEF;

			case Float:
				if(Character.isDigit(c))
					return State.Float;	
				else if(c == 'e' || c == 'E')
					return State.E;
				else
					return State.UNDEF;


			case E:
				if(Character.isDigit(c))
					return State.floatE;	
				else if(c == '+' || c == '-')
					return State.EPlusMinus;
				else 
					return State.UNDEF;

			case EPlusMinus:
			case floatE:
				if(Character.isDigit(c))
					return State.floatE;	
				else 
					return State.UNDEF;
			
			default:
				return State.UNDEF;
			
		}
	}

	// if string that is passed is a state => return the state
	// else - return State.UNDEF
	private static State getID(String s) {
		State stateThatGetsReturned;
		switch(s) {
			case "iconst":
				stateThatGetsReturned = State.iconst;
				break;
			case "iload":
				stateThatGetsReturned = State.iload;
				break;
			case "istore":
				stateThatGetsReturned = State.istore;
				break;
			case "fconst":
				stateThatGetsReturned = State.fconst;
				break;
			case "fload":
				stateThatGetsReturned = State.fload;
				break;
			case "fstore":
				stateThatGetsReturned = State.fstore;
				break;
			case "iadd":
				stateThatGetsReturned = State.iadd;
				break; 
			case "isub": 
				stateThatGetsReturned = State.isub;
				break;
			case "imul":
				stateThatGetsReturned = State.imul;
				break;
			case "idiv":
				stateThatGetsReturned = State.idiv;
				break;
			case "fadd":
				stateThatGetsReturned = State.fadd;
				break;
			case "fsub":
				stateThatGetsReturned = State.fsub;
				break;
			case "fmul":
				stateThatGetsReturned = State.fmul;
				break;
			case "fdiv":
				stateThatGetsReturned = State.fdiv;
				break;
			case "intToFloat":
				stateThatGetsReturned = State.intToFloat;
				break;
			case "icmpeq":
				stateThatGetsReturned = State.icmpeq;
				break;
			case "icmpne":
				stateThatGetsReturned = State.icmpne;
				break;
			case "icmplt":
				stateThatGetsReturned = State.icmplt;
				break;
			case "icmple":
				stateThatGetsReturned = State.icmple;
				break;
			case "icmpgt":
				stateThatGetsReturned = State.icmpgt;
				break;
			case "icmpge":
				stateThatGetsReturned = State.icmpge;
				break;
			case "fcmpeq":
				stateThatGetsReturned = State.fcmpeq;
				break;
			case "fcmpne":
				stateThatGetsReturned = State.fcmpne;
				break;
			case "fcmplt":
				stateThatGetsReturned = State.fcmplt;
				break;
			case "fcmple": 
				stateThatGetsReturned = State.fcmple;
				break;
		    case "fcmpgt":	
		    	stateThatGetsReturned = State.fcmpgt;
				break;
		    case "fcmpge":
			    stateThatGetsReturned = State.fcmpge;
				break;
			case "goto":
				stateThatGetsReturned = State.Goto;
				break;
			case "invoke":
				stateThatGetsReturned = State.invoke;
				break;
			case "return":
				stateThatGetsReturned = State.Return;
				break;
			case "ireturn":
				stateThatGetsReturned = State.ireturn;
				break;
			case "freturn":
				stateThatGetsReturned = State.freturn;
				break;
			case "print":
				stateThatGetsReturned = State.print;
				break;	

			default:
				stateThatGetsReturned = State.UNDEF;
		}
		
		return stateThatGetsReturned;
	}
	
	public static void main(String[] argv) {
		
		setIO(argv[0], argv[1]);
		int i;

		while ( a != -1 ) // while "a" is not end-of-stream
		{
			i = driver(); // extract the next token
			if ( i == 0 || state == State.UNDEF || state == State.Id)
				displayln( t+" : Lexical Error, invalid token");
			else if ( i == 1 )
				displayln( t+"   : "+state.toString() );
			
		} 
		
		closeIO();
	}
}