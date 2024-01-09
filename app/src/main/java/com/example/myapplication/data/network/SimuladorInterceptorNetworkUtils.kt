package com.example.myapplication.data.network
import okhttp3.*
import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody


class SimuladorInterceptorNetworkUtils(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
            // Simular una respuesta de éxito desde la API
            val simulatedResponseJson = """
                {
                    "message": {
                        "affenpinscher": [],
                        "african": [],
                        "airedale": [],
                        "akita": [],
                        "appenzeller": [],
                        "australian": [
                            "shepherd"
                        ],
                        "basenji": [],
                        "beagle": [],
                        "bluetick": [],
                        "borzoi": [],
                        "bouvier": [],
                        "boxer": [],
                        "brabancon": [],
                        "briard": [],
                        "buhund": [
                            "norwegian"
                        ],
                        "bulldog": [
                            "boston",
                            "english",
                            "french"
                        ],
                        "bullterrier": [
                            "staffordshire"
                        ],
                        "cattledog": [
                            "australian"
                        ],
                        "chihuahua": [],
                        "chow": [],
                        "clumber": [],
                        "cockapoo": [],
                        "collie": [
                            "border"
                        ],
                        "coonhound": [],
                        "corgi": [
                            "cardigan"
                        ],
                        "cotondetulear": [],
                        "dachshund": [],
                        "dalmatian": [],
                        "dane": [
                            "great"
                        ],
                        "deerhound": [
                            "scottish"
                        ],
                        "dhole": [],
                        "dingo": [],
                        "doberman": [],
                        "elkhound": [
                            "norwegian"
                        ],
                        "entlebucher": [],
                        "eskimo": [],
                        "finnish": [
                            "lapphund"
                        ],
                        "frise": [
                            "bichon"
                        ],
                        "germanshepherd": [],
                        "greyhound": [
                            "italian"
                        ],
                        "groenendael": [],
                        "havanese": [],
                        "hound": [
                            "afghan",
                            "basset",
                            "blood",
                            "english",
                            "ibizan",
                            "plott",
                            "walker"
                        ],
                        "husky": [],
                        "keeshond": [],
                        "kelpie": [],
                        "komondor": [],
                        "kuvasz": [],
                        "labradoodle": [],
                        "labrador": [],
                        "leonberg": [],
                        "lhasa": [],
                        "malamute": [],
                        "malinois": [],
                        "maltese": [],
                        "mastiff": [
                            "bull",
                            "english",
                            "tibetan"
                        ],
                        "mexicanhairless": [],
                        "mix": [],
                        "mountain": [
                            "bernese",
                            "swiss"
                        ],
                        "newfoundland": [],
                        "otterhound": [],
                        "ovcharka": [
                            "caucasian"
                        ],
                        "papillon": [],
                        "pekinese": [],
                        "pembroke": [],
                        "pinscher": [
                            "miniature"
                        ],
                        "pitbull": [],
                        "pointer": [
                            "german",
                            "germanlonghair"
                        ],
                        "pomeranian": [],
                        "poodle": [
                            "medium",
                            "miniature",
                            "standard",
                            "toy"
                        ],
                        "pug": [],
                        "puggle": [],
                        "pyrenees": [],
                        "redbone": [],
                        "retriever": [
                            "chesapeake",
                            "curly",
                            "flatcoated",
                            "golden"
                        ],
                        "ridgeback": [
                            "rhodesian"
                        ],
                        "rottweiler": [],
                        "saluki": [],
                        "samoyed": [],
                        "schipperke": [],
                        "schnauzer": [
                            "giant",
                            "miniature"
                        ],
                        "segugio": [
                            "italian"
                        ],
                        "setter": [
                            "english",
                            "gordon",
                            "irish"
                        ],
                        "sharpei": [],
                        "sheepdog": [
                            "english",
                            "shetland"
                        ],
                        "shiba": [],
                        "shihtzu": [],
                        "spaniel": [
                            "blenheim",
                            "brittany",
                            "cocker",
                            "irish",
                            "japanese",
                            "sussex",
                            "welsh"
                        ],
                        "spitz": [
                            "japanese"
                        ],
                        "springer": [
                            "english"
                        ],
                        "stbernard": [],
                        "terrier": [
                            "american",
                            "australian",
                            "bedlington",
                            "border",
                            "cairn",
                            "dandie",
                            "fox",
                            "irish",
                            "kerryblue",
                            "lakeland",
                            "norfolk",
                            "norwich",
                            "patterdale",
                            "russell",
                            "scottish",
                            "sealyham",
                            "silky",
                            "tibetan",
                            "toy",
                            "welsh",
                            "westhighland",
                            "wheaten",
                            "yorkshire"
                        ],
                        "tervuren": [],
                        "vizsla": [],
                        "waterdog": [
                            "spanish"
                        ],
                        "weimaraner": [],
                        "whippet": [],
                        "wolfhound": [
                            "irish"
                        ]
                    },
                    "status": "success"
                }
            """.trimIndent()





            // Construir una respuesta con código 200 (OK)
            val simulatedResponse = Response.Builder()
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(simulatedResponseJson.ResponseBody.create("application/json".MediaType.parse()))
                .build()

            return simulatedResponse
        }
    }

