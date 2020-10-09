package cl.arlanditech.dota2calculator

import cl.arlanditech.dota2calculator.fragments.heroes.model.api.APIHeroes
import cl.arlanditech.dota2calculator.fragments.heroes.model.api.response.BaseResponse
import cl.arlanditech.dota2calculator.fragments.heroes.model.api.response.HeroResponse
import cl.arlanditech.dota2calculator.model.Hero
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.full.memberProperties


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RetrofitTest {
    private lateinit var server: MockWebServer

    @Before
    @Throws(Exception::class)
    fun startServer() {
        server = MockWebServer()
        server.start()
    }

    @After
    @Throws(java.lang.Exception::class)
    fun stopServer() {
        server.shutdown()
    }

    @Test
    fun testResponseSuccessful() {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(APIClient.BASE_URL)
            .build()

        val service = retrofit.create(APIHeroes::class.java)
        val call: Call<BaseResponse> = service.getHeroes()
        assertTrue(call.execute().isSuccessful)
    }

    @Test
    fun testResponseBody() {
        server.enqueue(MockResponse().setResponseCode(200).setBody(getRawBody()))
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(server.url("/").toString())
            .build()

        val service = retrofit.create(APIHeroes::class.java)
        val call: Call<BaseResponse> = service.getHeroes()

        call.enqueue(object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<BaseResponse>?, response: Response<BaseResponse>?) {
                if (response?.isSuccessful!!) {
                    val heroes: ArrayList<Hero> = ArrayList()
                    for (prop in BaseResponse::class.memberProperties) {
                        val heroResponse: HeroResponse = prop.get(response.body()!!) as HeroResponse
                        val hero = Hero.getHeroFrom(heroResponse)
                        assertTrue(heroes.add(hero))
                    }
                }
            }
        })


    }
    
    private fun getRawBody(): String {
        return "{\n" +
                "    \"1\": {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"npc_dota_hero_antimage\",\n" +
                "        \"displayName\": \"Anti-Mage\",\n" +
                "        \"shortName\": \"antimage\",\n" +
                "        \"abilities\": [\n" +
                "            {\n" +
                "                \"slot\": 1,\n" +
                "                \"abilityId\": 5003\n" +
                "            },\n" +
                "            {\n" +
                "                \"slot\": 2,\n" +
                "                \"abilityId\": 5004\n" +
                "            },\n" +
                "            {\n" +
                "                \"slot\": 3,\n" +
                "                \"abilityId\": 7314\n" +
                "            },\n" +
                "            {\n" +
                "                \"slot\": 4,\n" +
                "                \"abilityId\": 6251\n" +
                "            },\n" +
                "            {\n" +
                "                \"slot\": 5,\n" +
                "                \"abilityId\": 6251\n" +
                "            },\n" +
                "            {\n" +
                "                \"slot\": 6,\n" +
                "                \"abilityId\": 5006\n" +
                "            }\n" +
                "        ],\n" +
                "        \"roles\": [\n" +
                "            {\n" +
                "                \"roleId\": 0,\n" +
                "                \"level\": 3\n" +
                "            },\n" +
                "            {\n" +
                "                \"roleId\": 1,\n" +
                "                \"level\": 3\n" +
                "            },\n" +
                "            {\n" +
                "                \"roleId\": 2,\n" +
                "                \"level\": 1\n" +
                "            }\n" +
                "        ],\n" +
                "        \"talents\": [\n" +
                "            {\n" +
                "                \"slot\": 0,\n" +
                "                \"gameVersionId\": 135,\n" +
                "                \"abilityId\": 6250\n" +
                "            },\n" +
                "            {\n" +
                "                \"slot\": 1,\n" +
                "                \"gameVersionId\": 135,\n" +
                "                \"abilityId\": 6119\n" +
                "            },\n" +
                "            {\n" +
                "                \"slot\": 2,\n" +
                "                \"gameVersionId\": 135,\n" +
                "                \"abilityId\": 6606\n" +
                "            },\n" +
                "            {\n" +
                "                \"slot\": 3,\n" +
                "                \"gameVersionId\": 135,\n" +
                "                \"abilityId\": 7386\n" +
                "            },\n" +
                "            {\n" +
                "                \"slot\": 4,\n" +
                "                \"gameVersionId\": 135,\n" +
                "                \"abilityId\": 6800\n" +
                "            },\n" +
                "            {\n" +
                "                \"slot\": 5,\n" +
                "                \"gameVersionId\": 135,\n" +
                "                \"abilityId\": 6012\n" +
                "            },\n" +
                "            {\n" +
                "                \"slot\": 6,\n" +
                "                \"gameVersionId\": 135,\n" +
                "                \"abilityId\": 6607\n" +
                "            },\n" +
                "            {\n" +
                "                \"slot\": 7,\n" +
                "                \"gameVersionId\": 135,\n" +
                "                \"abilityId\": 6353\n" +
                "            }\n" +
                "        ],\n" +
                "        \"stat\": {\n" +
                "            \"gameVersionId\": 135,\n" +
                "            \"enabled\": true,\n" +
                "            \"heroUnlockOrder\": 1.0,\n" +
                "            \"team\": true,\n" +
                "            \"cmEnabled\": true,\n" +
                "            \"newPlayerEnabled\": true,\n" +
                "            \"attackType\": \"Melee\",\n" +
                "            \"startingArmor\": 2.36,\n" +
                "            \"startingMagicArmor\": 25.0,\n" +
                "            \"startingDamageMin\": 29.0,\n" +
                "            \"startingDamageMax\": 33.0,\n" +
                "            \"attackRate\": 1.4,\n" +
                "            \"attackAnimationPoint\": 0.3,\n" +
                "            \"attackAcquisitionRange\": 600.0,\n" +
                "            \"attackRange\": 150.0,\n" +
                "            \"primaryAttribute\": \"agi\",\n" +
                "            \"heroPrimaryAttribute\": 1,\n" +
                "            \"strengthBase\": 23.0,\n" +
                "            \"strengthGain\": 1.3,\n" +
                "            \"intelligenceBase\": 12.0,\n" +
                "            \"intelligenceGain\": 1.8,\n" +
                "            \"agilityBase\": 24.0,\n" +
                "            \"agilityGain\": 2.8,\n" +
                "            \"hpRegen\": 0.25,\n" +
                "            \"mpRegen\": 0.0,\n" +
                "            \"moveSpeed\": 310.0,\n" +
                "            \"moveTurnRate\": 0.5,\n" +
                "            \"hpBarOffset\": 0.0,\n" +
                "            \"visionDaytimeRange\": 1800.0,\n" +
                "            \"visionNighttimeRange\": 800.0,\n" +
                "            \"complexity\": 1\n" +
                "        },\n" +
                "        \"language\": {\n" +
                "            \"heroId\": 1,\n" +
                "            \"gameVersionId\": 135,\n" +
                "            \"languageId\": 0,\n" +
                "            \"displayName\": \"Anti-Mage\",\n" +
                "            \"bio\": \"The monks of Turstarkuri watched the rugged valleys below their mountain monastery as wave after wave of invaders swept through the lower kingdoms. Ascetic and pragmatic, in their remote monastic eyrie they remained aloof from mundane strife, wrapped in meditation that knew no gods or elements of magic. Then came the Legion of the Dead God, crusaders with a sinister mandate to replace all local worship with their Unliving Lord's poisonous nihilosophy. From a landscape that had known nothing but blood and battle for a thousand years, they tore the souls and bones of countless fallen legions and pitched them against Turstarkuri. The monastery stood scarcely a fortnight against the assault, and the few monks who bothered to surface from their meditations believed the invaders were but demonic visions sent to distract them from meditation. They died where they sat on their silken cushions. Only one youth survived--a pilgrim who had come as an acolyte, seeking wisdom, but had yet to be admitted to the monastery. He watched in horror as the monks to whom he had served tea and nettles were first slaughtered, then raised to join the ranks of the Dead God's priesthood. With nothing but a few of Turstarkuri's prized dogmatic scrolls, he crept away to the comparative safety of other lands, swearing to obliterate not only the Dead God's magic users--but to put an end to magic altogether. \",\n" +
                "            \"hype\": \"Should Anti-Mage have the opportunity to gather his full strength, few can stop his assaults. Draining mana from enemies with every strike or teleporting short distances to escape an ambush, cornering him is a challenge for any foe.\"\n" +
                "        },\n" +
                "        \"aliases\": [\n" +
                "            \"am\"\n" +
                "        ]\n" +
                "    }\n"+
                "}"
    }
}