/*
 *  BIP44
 *  Copyright (C) 2017-2019 Alan Evans, NovaCrypto
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *  Original source: https://github.com/NovaCrypto/BIP44
 *  You can contact the authors via github issues.
 */

package io.github.novacrypto;

import io.github.novacrypto.bip44.M;
import org.junit.Test;

import static io.github.novacrypto.bip44.BIP44.m;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public final class MTests {

    @Test
    public void cached() {
        assertSame(m(), m());
    }

    @Test
    public void toStringIsM() {
        assertEquals("m", m().toString());
    }

    @Test
    public void toStringIsCached() {
        final M m = m();
        assertSame(m.toString(), m.toString());
    }
}