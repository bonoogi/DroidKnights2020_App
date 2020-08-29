package com.droidknights.app2020.db.prepackage

import android.content.Context
import com.droidknights.app2020.data.Session
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import timber.log.Timber

class PrePackagedDbImpl(
    val context: Context,
    val assetsName: String
) : PrePackagedDb {

    private val sessionList: PrePackagedSessionList = loadJson(assetsName)
        //context.readJsonStringFromAsset(assetsName)?.toSessionList().orEmpty()

    private fun String.toSessionList(): List<Session> {
        return Gson().fromJson(this, PrePackagedSessionList::class.java).session
    }

    override suspend fun getSessionList(): List<Session> {
        return sessionList.session
    }

    override suspend fun getSessionById(id: String): Session? {
        return sessionList.session.find { it.id == id }
    }

    private fun Context.readJsonStringFromAsset(assetsName: String): String? {
        return try {
            assets.open(assetsName)
                .bufferedReader()
                .use { it.readText() }
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
    }

    private class PrePackagedSessionList(val session: List<Session>)

    inline fun <reified ENTITY> loadJson(fileName: String): ENTITY {
        val result = try {
            context.assets.open(fileName)
                .bufferedReader()
                .use { it.readText() }

        } catch (e: Exception) {
            Timber.e(e)
            null
        }

        val gson = GsonBuilder().create()
        val collectionType = object : TypeToken<ENTITY>() {}.type
        return gson.fromJson(result, collectionType) as ENTITY
    }
}