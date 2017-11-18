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

import io.github.novacrypto.bip32.PrivateKey;
import io.github.novacrypto.bip32.derivation.Derive;
import io.github.novacrypto.bip32.networks.Bitcoin;
import io.github.novacrypto.bip39.SeedCalculator;
import io.github.novacrypto.bip44.AddressIndex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static io.github.novacrypto.bip44.Purpose.purpose;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


@RunWith(Parameterized.class)
public final class DeriveFromRootTests {

    private static final PrivateKey rootKey = PrivateKey.fromSeed(new SeedCalculator().calculateSeed("dial repeat accuse hen century accident route indicate middle render gate dignity",
            ""), Bitcoin.MAIN_NET);

    private final String path;
    private final AddressIndex addressIndex;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"m/44'/0'/0'/0/0", purpose(44).coinType(0).account(0).external().address(0)},
                {"m/44'/0'/0'/0/1", purpose(44).coinType(0).account(0).external().address(1)},
                {"m/44'/0'/0'/1/0", purpose(44).coinType(0).account(0).internal().address(0)},
                {"m/44'/0'/1'/0/0", purpose(44).coinType(0).account(1).external().address(0)},
                {"m/44'/1'/0'/0/0", purpose(44).coinType(1).account(0).external().address(0)},
                {"m/49'/0'/0'/0/0", purpose(49).coinType(0).account(0).external().address(0)}
        });
    }

    public DeriveFromRootTests(String path, AddressIndex addressIndex) {
        this.path = path;
        this.addressIndex = addressIndex;
    }

    @Test
    public void pathMatches() {
        assertEquals(path, addressIndex.toString());
    }

    @Test
    public void derive() {
        String expected = rootKey.derive(path).neuter().p2pkhAddress();
        String actual = rootKey.derive(addressIndex, AddressIndex.DERIVATION).neuter().p2pkhAddress();
        assertEquals(expected, actual);
    }

    @Test
    public void deriveWithCache() {
        final Derive<PrivateKey> withCache = rootKey.deriveWithCache();
        PrivateKey expected = withCache.derive(path);
        PrivateKey actual = withCache.derive(addressIndex, AddressIndex.DERIVATION);
        assertSame(expected, actual);
    }
}