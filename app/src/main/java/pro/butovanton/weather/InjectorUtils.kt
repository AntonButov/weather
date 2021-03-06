/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pro.butovanton.weather

import android.app.Application
import android.content.Context
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import pro.butovanton.weather.Data.*
import pro.butovanton.weather.Domain.interactor.InteractorCitys
import pro.butovanton.weather.Domain.interactor.InteractorMain
import pro.butovanton.weather.Domain.interactor.InteractorTemper
import pro.butovanton.weather.Observer.Observable
import pro.butovanton.weather.Observer.Observer
import pro.butovanton.weather.Presentantion.ViewModels.TemperViewModel


object InjectorUtils {

    fun provideDb(context: Context): AppDatabase = AppDatabase.createBd(context)

    fun provideRepo(context: Context) = Repo.getInstance(provideDb(context).getDao())

    fun provideInteractorMain(context: Context): InteractorMain = InteractorMain(provideRepo(context) as DataWayMain, provideRepo(context) as Observable)

    fun provideInteractorTemper(context: Context) = InteractorTemper(provideRepo(context) as DataWayTemper)

    fun provideInteractorCitys(context: Context) = InteractorCitys(provideRepo(context) as DataWayCitys)

    class TemperModelFactory(private val app: Application): NewInstanceFactory() {
        @NonNull
        override fun <T : ViewModel?> create(@NonNull modelClass: Class<T>): T {
            return if (modelClass == TemperViewModel::class.java) {
                TemperViewModel(app, provideInteractorTemper(app)) as T
            } else throw Exception()
        }
    }

}