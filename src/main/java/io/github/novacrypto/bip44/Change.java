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

public final class Change {
    private final Account account;
    private final int change;
    private final String string;

    Change(final Account account, final int change) {
        this.account = account;
        this.change = change;
        string = String.format("%s/%d", account, change);
    }

    public int getValue() {
        return change;
    }

    public Account getParent() {
        return account;
    }

    @Override
    public String toString() {
        return string;
    }

    public AddressIndex address(final int addressIndex) {
        return new AddressIndex(this, addressIndex);
    }
}