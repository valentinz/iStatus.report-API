/**
 * Copyright (C) 2015 Valentin Zickner <valentin.zickner@helfenkannjeder.de>
 *
 * This file is part of HelfenKannJeder.
 *
 * HelfenKannJeder is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * HelfenKannJeder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with HelfenKannJeder. If not, see <http://www.gnu.org/licenses/>.
 */
package de.helfenkannjeder.istatus.api;

import java.util.List;

/**
 * @author Valentin Zickner <valentin.zickner@helfenkannjeder.de>
 *
 */
public interface StatusReportApi {

	RequestState<User> login();

	RequestState<Boolean> setStatus(UserStatus status);

	RequestState<List<UserStatus>> getStatus(User status);

}
