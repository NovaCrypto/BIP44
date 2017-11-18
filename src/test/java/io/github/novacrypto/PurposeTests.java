/*
 *  BIP44
 *  Copyright (C) 2017 Alan Evans, NovaCrypto
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

import io.github.novacrypto.bip44.Purpose;
import org.junit.Test;

import static io.github.novacrypto.bip44.Purpose.purpose;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public final class PurposeTests {

    @Test
    public void purpose44() {
        assertEquals("m/44'", purpose(44).toString());
    }

    @Test
    public void purpose49() {
        assertEquals("m/49'", purpose(49).toString());
    }

    @Test
    public void purpose44getValue() {
        assertEquals(44, purpose(44).getValue());
    }

    @Test
    public void purpose49getValue() {
        assertEquals(49, purpose(49).getValue());
    }

    @Test
    public void purposeStringIsPreCalculated() {
        final Purpose purpose = purpose(44);
        assertSame(purpose.toString(), purpose.toString());
    }
}