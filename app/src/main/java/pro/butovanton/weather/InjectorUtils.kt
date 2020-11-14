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
import pro.butovanton.weather.Data.AppDatabase
import pro.butovanton.weather.Data.Repo
import pro.butovanton.weather.Domain.interactor.InteractorCitys
import pro.butovanton.weather.Domain.interactor.InteractorMain
import pro.butovanton.weather.Domain.interactor.InteractorTemper
import pro.butovanton.weather.Presentantion.ViewModels.TemperViewModel


object InjectorUtils {

    fun provideDb(context: Context): AppDatabase {
        return AppDatabase.createBd(context)
    }

    fun provideRepo(context: Context): Repo {
        return Repo(provideDb(context).getDao())
    }

    fun provideInteractor(context: Context): InteractorMain {
        return InteractorMain(provideRepo(context))
    }

    fun provideInteractorTemper(context: Context): InteractorTemper {
        return InteractorTemper(provideRepo(context))
    }

    fun provideInteractorCitys(context: Context): InteractorCitys {
        return InteractorCitys(provideRepo(context))
    }

    class TemperModelFactory(private val app: Application): NewInstanceFactory() {
        @NonNull
        override fun <T : ViewModel?> create(@NonNull modelClass: Class<T>): T {
            return if (modelClass == TemperViewModel::class.java) {
                TemperViewModel(app, provideInteractorTemper(app)) as T
            } else throw Exception()
        }
    }

}