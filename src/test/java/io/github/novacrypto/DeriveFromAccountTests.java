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

import io.github.novacrypto.bip32.ExtendedPrivateKey;
import io.github.novacrypto.bip32.ExtendedPublicKey;
import io.github.novacrypto.bip32.derivation.Derive;
import io.github.novacrypto.bip32.networks.Bitcoin;
import io.github.novacrypto.bip39.SeedCalculator;
import io.github.novacrypto.bip44.Account;
import io.github.novacrypto.bip44.AddressIndex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

import static io.github.novacrypto.bip44.BIP44.m;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


@RunWith(Parameterized.class)
public final class DeriveFromAccountTests {

    private final Account account;
    private final String accountPath;
    private final String path;
    private final AddressIndex addressIndex;

    interface Map extends Function<Account, AddressIndex> {

    }

    private static final ExtendedPrivateKey rootKey = ExtendedPrivateKey.fromSeed(new SeedCalculator().calculateSeed("dial repeat accuse hen century accident route indicate middle render gate dignity",
            ""), Bitcoin.MAIN_NET);


    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"m/44'/0'/0'", "/0/0", m().purpose(44).coinType(0).account(0), (Map) (Account acc) -> acc.external().address(0)},
                {"m/44'/0'/0'", "/0/1", m().purpose(44).coinType(0).account(0), (Map) (Account acc) -> acc.external().address(1)},
                {"m/44'/0'/0'", "/1/0", m().purpose(44).coinType(0).account(0), (Map) (Account acc) -> acc.internal().address(0)},
                {"m/44'/0'/1'", "/0/0", m().purpose(44).coinType(0).account(1), (Map) (Account acc) -> acc.external().address(0)},
                {"m/44'/1'/0'", "/0/0", m().purpose(44).coinType(1).account(0), (Map) (Account acc) -> acc.external().address(0)},
                {"m/49'/0'/0'", "/0/0", m().purpose(49).coinType(0).account(0), (Map) (Account acc) -> acc.external().address(0)}
        });
    }

    public DeriveFromAccountTests(String accountPath, String path, Account account, Map createAddressIndex) {
        this.accountPath = accountPath;
        this.path = accountPath + path;
        this.account = account;
        this.addressIndex = createAddressIndex.apply(account);
    }

    @Test
    public void pathMatches() {
        assertEquals(accountPath, account.toString());
        assertEquals(path, addressIndex.toString());
    }

    @Test
    public void deriveAccount() {
        String expected = rootKey.derive(accountPath).extendedBase58();
        String actual = rootKey.derive(account, Account.DERIVATION).extendedBase58();
        assertEquals(expected, actual);
    }

    @Test
    public void deriveAccountWithCache() {
        final Derive<ExtendedPrivateKey> withCache = rootKey.deriveWithCache();
        ExtendedPrivateKey expected = withCache.derive(accountPath);
        ExtendedPrivateKey actual = withCache.derive(account, Account.DERIVATION);
        assertSame(expected, actual);
    }

    @Test
    public void deriveFromAccountPrivate() {
        String expected = rootKey.derive(path).neuter().p2pkhAddress();

        ExtendedPrivateKey accountPrivate = rootKey.derive(accountPath);
        String actual = accountPrivate.derive(addressIndex, AddressIndex.DERIVATION_FROM_ACCOUNT).neuter().p2pkhAddress();
        assertEquals(expected, actual);
    }

    @Test
    public void deriveFromAccountPublic() {
        String expected = rootKey.derive(path).neuter().p2pkhAddress();

        ExtendedPublicKey accountPublic = rootKey.derive(accountPath).neuter();
        String actual = accountPublic.derive(addressIndex, AddressIndex.DERIVATION_FROM_ACCOUNT).p2pkhAddress();
        assertEquals(expected, actual);
    }

    @Test
    public void deriveWithCache() {
        Derive<ExtendedPrivateKey> accountPrivate = rootKey.derive(accountPath).deriveWithCache();
        ExtendedPrivateKey expected = accountPrivate.derive(addressIndex, AddressIndex.DERIVATION_FROM_ACCOUNT);
        ExtendedPrivateKey actual = accountPrivate.derive(addressIndex, AddressIndex.DERIVATION_FROM_ACCOUNT);
        assertSame(expected, actual);
    }
}