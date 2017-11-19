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

package io.github.novacrypto.bip44;

import static io.github.novacrypto.bip32.Index.isHardened;

public final class CoinType {
    private final Purpose purpose;
    private final int coinType;
    private final String string;

    CoinType(final Purpose purpose, final int coinType) {
        if (isHardened(coinType))
            throw new IllegalArgumentException();
        this.purpose = purpose;
        this.coinType = coinType;
        string = String.format("%s/%d'", purpose, coinType);
    }

    public int getValue() {
        return coinType;
    }

    public Purpose getParent() {
        return purpose;
    }

    @Override
    public String toString() {
        return string;
    }

    public Account account(final int account) {
        return new Account(this, account);
    }
}