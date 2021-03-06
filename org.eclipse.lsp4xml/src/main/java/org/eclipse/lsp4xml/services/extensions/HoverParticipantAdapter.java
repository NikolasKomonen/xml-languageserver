/**
 *  Copyright (c) 2018 Angelo ZERR.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *
 *  Contributors:
 *  Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 */
package org.eclipse.lsp4xml.services.extensions;

import org.eclipse.lsp4j.Hover;

/**
 * Hover participant adapter.
 *
 */
public class HoverParticipantAdapter implements IHoverParticipant {

	@Override
	public Hover onTag(IHoverRequest request) throws Exception {
		return null;
	}

	@Override
	public Hover onAttributeName(IHoverRequest request) throws Exception {
		return null;
	}

	@Override
	public Hover onAttributeValue(IHoverRequest request) throws Exception {
		return null;
	}

}
