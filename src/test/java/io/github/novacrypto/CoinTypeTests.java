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

import io.github.novacrypto.bip44.CoinType;
import io.github.novacrypto.bip44.Purpose;
import org.junit.Test;

import static io.github.novacrypto.bip44.BIP44.m;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public final class CoinTypeTests {

    @Test
    public void coinType0_Bitcoin() {
        assertEquals("m/44'/0'", m().purpose(44).coinType(0).toString());
    }

    @Test
    public void coinType1_BitcoinTestNet() {
        assertEquals("m/44'/1'", m().purpose(44).coinType(1).toString());
    }

    @Test
    public void coinType0getValue() {
        assertEquals(0, m().purpose(44).coinType(0).getValue());
    }

    @Test
    public void coinType1getValue() {
        assertEquals(1, m().purpose(44).coinType(1).getValue());
    }

    @Test
    public void getParent() {
        final Purpose expected = m().purpose(44);
        assertSame(expected, expected.coinType(0).getParent());
    }

    @Test
    public void prupose49_coinType1_Bitcoin() {
        assertEquals("m/49'/1'", m().purpose(49).coinType(1).toString());
    }

    @Test
    public void coinTypeStringIsPreCalculated() {
        final CoinType coinType = m().purpose(44).coinType(0);
        assertSame(coinType.toString(), coinType.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void coinType_negative() {
        m().purpose(44).coinType(-1);
    }
}