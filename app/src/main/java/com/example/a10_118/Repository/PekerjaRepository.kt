package com.example.a10_118.Repository

import com.example.a10_118.Model.Pekerja
import java.io.IOException

interface PekerjaRepository {
    suspend fun getPekerja(): List<Pekerja>
    suspend fun insertPekerja(pekerja: Pekerja)
    suspend fun updatePekerja(id_pekerja: String, pekerja: Pekerja)
    suspend fun deletePekerja(id_pekerja: String)
    suspend fun getPekerjaById(id_pekerja: String): Pekerja
}

class NetworkPekerjaRepository(
    private val PekerjaAPIService: PekerjaService
) : PekerjaRepository {

    override suspend fun getPekerja(): List<Pekerja> {
        try {
            return PekerjaAPIService.getPekerja()
        } catch (e: IOException) {
            throw IOException("Failed to fetch Pekerja list. Network error occurred.", e)
        }
    }

    override suspend fun getPekerjaById(id_pekerja: String): Pekerja {
        try {
            return PekerjaAPIService.getPekerjaById(id_pekerja)
        } catch (e: IOException) {
            throw IOException("Failed to fetch Pekerja with id_pekerja: $id_pekerja. Network error occurred.", e)
        }
    }

    override suspend fun insertPekerja(pekerja: Pekerja) {
        try {
            val response = PekerjaAPIService.insertPekerja(pekerja)
            if (!response.isSuccessful) {
                throw IOException("Failed to insert pekerja. HTTP Status code: ${response.code()}")
            }
        } catch (e: IOException) {
            throw IOException("Failed to insert pekerja. Network error occurred.", e)
        }
    }

    override suspend fun updatePekerja(id_pekerja: String, pekerja: Pekerja) {
        try {
            val response = PekerjaAPIService.updatepekerja(id_pekerja,pekerja)
            if (!response.isSuccessful) {
                throw IOException("Failed to update pekerja with id_pekerja: $id_pekerja. HTTP Status code: ${response.code()}")
            }
        } catch (e: IOException) {
            throw IOException("Failed to update pekerja. Network error occurred.", e)
        }
    }

    override suspend fun deletePekerja(id_pekerja: String) {
        try {
            val response = PekerjaAPIService.deletepekerja(id_pekerja)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete pekerja with id_pekerja: $id_pekerja. HTTP Status code: ${response.code()}")
            }
        } catch (e: IOException) {
            throw IOException("Failed to delete pekerja. Network error occurred.", e)
        }
    }
}