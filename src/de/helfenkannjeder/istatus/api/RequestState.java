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

import java.util.LinkedList;
import java.util.List;

/**
 * @author Valentin Zickner <valentin.zickner@helfenkannjeder.de>
 *
 */
public class RequestState<T> {

	private boolean finished;
	private T load;
	private List<RequestStateListener> listener = new LinkedList<RequestStateListener>();
	private Result result = Result.ERROR_NO_STATUS_SET;

	public static enum Result {
		SUCCESS, ERROR_NO_STATUS_SET,
	}

	public RequestState(Result result, T load) {
		this.result = result;
		this.load = load;
		this.finished = true;
	}

	public RequestState(Result result) {
		this.result = result;
		this.finished = true;
	}

	public RequestState() {
	}

	private void setFinished() {
		synchronized (this.listener) {

			this.finished = true;
			for (RequestStateListener listener : this.listener) {
				listener.requestComplete();
			}
		}
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinalLoad(Result result, T load) {
		this.result = result;
		this.load = load;
		setFinished();
	}

	public void setFinalLoad(Result result) {
		this.result = result;
		setFinished();
	}

	public T getLoad() {
		return load;
	}

	public Result getResult() {
		return result;
	}

	public void registerListener(RequestStateListener listener) {
		synchronized (this.listener) {
			this.listener.add(listener);
			if (finished == true) {
				listener.requestComplete();
			}
		}

	}
}
