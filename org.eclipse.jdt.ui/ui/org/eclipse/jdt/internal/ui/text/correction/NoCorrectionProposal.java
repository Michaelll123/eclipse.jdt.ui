/*******************************************************************************
 * Copyright (c) 2000, 2002 International Business Machines Corp. and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v0.5 
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v05.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 ******************************************************************************/

package org.eclipse.jdt.internal.ui.text.correction;

import org.eclipse.jdt.core.compiler.IProblem;

import org.eclipse.jdt.internal.corext.refactoring.NullChange;


public class NoCorrectionProposal extends ChangeCorrectionProposal {

	private ProblemPosition fProblemPosition;

	public NoCorrectionProposal(ProblemPosition problemPosition) {
		super("No correction available", new NullChange(), 0);
		fProblemPosition= problemPosition;
	}


	/*
	 * @see ICompletionProposal#getAdditionalProposalInfo()
	 */
	public String getAdditionalProposalInfo() {
		StringBuffer buf= new StringBuffer();
		buf.append("<p><b>"); //$NON-NLS-1$
		buf.append(getErrorCode(fProblemPosition.getId()));
		buf.append("</b></p>"); //$NON-NLS-1$
		buf.append("<p>"); //$NON-NLS-1$
		buf.append(fProblemPosition.getMessage());
		buf.append("</p>"); //$NON-NLS-1$
		String[] arg= fProblemPosition.getArguments();
		if (arg != null) {
			for (int i= 0; i < arg.length; i++) {
				buf.append("<p>"); //$NON-NLS-1$
				buf.append(arg[i]);
				buf.append("</p>");				 //$NON-NLS-1$
			}
		}
	
		return buf.toString();
	}
	
	private String getErrorCode(int code) {
		StringBuffer buf= new StringBuffer();
		
		if ((code & IProblem.TypeRelated) != 0) {
			buf.append("TypeRelated + "); //$NON-NLS-1$
		}
		if ((code & IProblem.FieldRelated) != 0) {
			buf.append("FieldRelated + "); //$NON-NLS-1$
		}
		if ((code & IProblem.ConstructorRelated) != 0) {
			buf.append("ConstructorRelated + "); //$NON-NLS-1$
		}
		if ((code & IProblem.MethodRelated) != 0) {
			buf.append("MethodRelated + "); //$NON-NLS-1$
		}
		if ((code & IProblem.ImportRelated) != 0) {
			buf.append("ImportRelated + "); //$NON-NLS-1$
		}
		if ((code & IProblem.Internal) != 0) {
			buf.append("Internal + "); //$NON-NLS-1$
		}
		if ((code & IProblem.Syntax) != 0) {
			buf.append("Syntax + "); //$NON-NLS-1$
		}
		buf.append(code & IProblem.IgnoreCategoriesMask);
		
		return buf.toString();
	}


}
