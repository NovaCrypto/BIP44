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
import io.github.novacrypto.bip44.CoinType;
import org.junit.Test;

import static io.github.novacrypto.bip44.BIP44.m;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public final class AccountTests {

    @Test
    public void account0() {
        assertEquals("m/44'/0'/0'",
                m().purpose(44).coinType(0).account(0)
                        .toString());
    }

    @Test
    public void account1() {
        assertEquals("m/44'/0'/1'",
                m().purpose(44).coinType(0).account(1)
                        .toString());
    }

    @Test
    public void account0getValue() {
        assertEquals(0,
                m().purpose(44).coinType(0).account(0)
                        .getValue());
    }

    @Test
    public void account1getValue() {
        assertEquals(1,
                m().purpose(44).coinType(0).account(1)
                        .getValue());
    }

    @Test
    public void getParent() {
        final CoinType expected = m().purpose(44).coinType(0);
        assertSame(expected, expected.account(0).getParent());
    }

    @Test
    public void alternativePurposeAndCoinType() {
        assertEquals("m/49'/1'/10'",
                m().purpose(49).coinType(1).account(10)
                        .toString());
    }

    @Test
    public void stringIsPreCalculated() {
        final Account account = m().purpose(44).coinType(0).account(0);
        assertSame(account.toString(), account.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void account_negative() {
        m().purpose(44).coinType(0).account(-1);
    }
}