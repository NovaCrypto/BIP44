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

import io.github.novacrypto.bip44.Account;
import io.github.novacrypto.bip44.Change;
import org.junit.Test;

import static io.github.novacrypto.bip44.Purpose.purpose;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public final class ChangeTests {

    @Test
    public void external() {
        assertEquals("m/44'/0'/0'/0",
                purpose(44).coinType(0).account(0).external()
                        .toString());
    }

    @Test
    public void internal() {
        assertEquals("m/44'/0'/0'/1",
                purpose(44).coinType(0).account(0).internal()
                        .toString());
    }

    @Test
    public void externalGetValue() {
        assertEquals(0,
                purpose(44).coinType(0).account(0).external()
                        .getValue());
    }

    @Test
    public void internalGetValue() {
        assertEquals(1,
                purpose(44).coinType(0).account(0).internal()
                        .getValue());
    }

    @Test
    public void getParent() {
        final Account expected = purpose(44).coinType(0).account(0);
        assertSame(expected, expected.internal().getParent());
    }

    @Test
    public void alternativePurposeAndCoinTypeAndAccount() {
        assertEquals("m/49'/1'/10'/1",
                purpose(49).coinType(1).account(10)
                        .internal()
                        .toString());
    }

    @Test
    public void stringIsPreCalculated() {
        final Change change = purpose(44).coinType(0).account(0).internal();
        assertSame(change.toString(), change.toString());
    }
}