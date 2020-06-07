using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Net;
using RestSharp;
using Newtonsoft.Json;

namespace WindowAttacher
{
    class ClientCommunicator
    {
        private static readonly HttpClient client = new HttpClient();
        private static Dictionary<int, String> idToName = new Dictionary<int, string>()
        {
            {266, "Aatrox"},
            {103, "Ahri"},
            {84, "Akali"},
            {12, "Alistar"},
            {32, "Amumu"},
            {34, "Anivia"},
            {1, "Annie"},
            {523, "Aphelios"},
            {22, "Ashe"},
            {136, "AurelionSol"},
            {268, "Azir"},
            {432, "Bard"},
            {53, "Blitzcrank"},
            {63, "Brand"},
            {201, "Braum"},
            {51, "Caitlyn"},
            {164, "Camille"},
            {69, "Cassiopeia"},
            {31, "ChoGath"},
            {42, "Corki"},
            {122, "Darius"},
            {131, "Diana"},
            {119, "Draven"},
            {36, "DrMundo"},
            {245, "Ekko"},
            {60, "Elise"},
            {28, "Evelynn"},
            {81, "Ezreal"},
            {9, "Fiddlesticks"},
            {114, "Fiora"},
            {105, "Fizz"},
            {3, "Galio"},
            {41, "Gangplank"},
            {86, "Garen"},
            {150, "Gnar"},
            {79, "Gragas"},
            {104, "Graves"},
            {120, "Hecarim"},
            {74, "Heimerdinger"},
            {420, "Illaoi"},
            {39, "Irelia"},
            {427, "Ivern"},
            {40, "Janna"},
            {59, "JarvanIV"},
            {24, "Jax"},
            {126, "Jayce"},
            {202, "Jhin"},
            {222, "Jinx"},
            {145, "Kaisa"},
            {429, "Kalista"},
            {43, "Karma"},
            {30, "Karthus"},
            {38, "Kassadin"},
            {55, "Katarina"},
            {10, "Kayle"},
            {141, "Kayn"},
            {85, "Kennen"},
            {121, "KhaZix"},
            {203, "Kindred"},
            {240, "Kled"},
            {96, "KogMaw"},
            {7, "Leblanc"},
            {64, "LeeSin"},
            {89, "Leona"},
            {127, "Lissandra"},
            {236, "Lucian"},
            {117, "Lulu"},
            {99, "Lux"},
            {54, "Malphite"},
            {90, "Malzahar"},
            {57, "Maokai"},
            {11, "MasterYi"},
            {21, "MissFortune"},
            {62, "MonkeyKing"},
            {82, "Mordekaiser"},
            {25, "Morgana"},
            {267, "Nami"},
            {75, "Nasus"},
            {111, "Nautilus"},
            {518, "Neeko"},
            {76, "Nidalee"},
            {56, "Nocturne"},
            {20, "Nunu"},
            {2, "Olaf"},
            {61, "Orianna"},
            {516, "Ornn"},
            {80, "Pantheon"},
            {78, "Poppy"},
            {555, "Pyke"},
            {246, "Qiyana"},
            {133, "Quinn"},
            {497, "Rakan"},
            {33, "Rammus"},
            {421, "RekSai"},
            {58, "Renekton"},
            {107, "Rengar"},
            {92, "Riven"},
            {68, "Rumble"},
            {13, "Ryze"},
            {113, "Sejuani"},
            {235, "Senna"},
            {875, "Sett"},
            {35, "Shaco"},
            {98, "Shen"},
            {102, "Shyvana"},
            {27, "Singed"},
            {14, "Sion"},
            {15, "Sivir"},
            {72, "Skarner"},
            {37, "Sona"},
            {16, "Soraka"},
            {50, "Swain"},
            {517, "Sylas"},
            {134, "Syndra"},
            {223, "TahmKench"},
            {163, "Taliyah"},
            {91, "Talon"},
            {44, "Taric"},
            {17, "Teemo"},
            {412, "Thresh"},
            {18, "Tristana"},
            {48, "Trundle"},
            {23, "Tryndamere"},
            {4, "TwistedFate"},
            {29, "Twitch"},
            {77, "Udyr"},
            {6, "Urgot"},
            {110, "Varus"},
            {67, "Vayne"},
            {45, "Veigar"},
            {161, "VelKoz"},
            {254, "Vi"},
            {112, "Viktor"},
            {8, "Vladimir"},
            {106, "Volibear"},
            {19, "Warwick"},
            {498, "Xayah"},
            {101, "Xerath"},
            {5, "XinZhao"},
            {157, "Yasuo"},
            {83, "Yorick"},
            {350, "Yuumi"},
            {154, "Zac"},
            {238, "Zed"},
            {115, "Ziggs"},
            {26, "Zilean"},
            {142, "Zoe"},
            {143, "Zyra"}
        };

        private static Dictionary<String, String> riotToLocalPosition = new Dictionary<string, string>()
        {
            {"top", "top" },
            {"jungle", "jungle" },
            {"middle", "mid" },
            {"bottom", "bot" },
            {"utility", "support" }
        };


        public async Task<ChampionPicksTurnAndPosition> getChampionsPicked()
        {
            ServicePointManager.Expect100Continue = true;
            ServicePointManager.SecurityProtocol = SecurityProtocolType.Tls12;
            System.Net.ServicePointManager.ServerCertificateValidationCallback =
            ((sender, certificate, chain, sslPolicyErrors) => true);
            var client = new RestClient("https://127.0.0.1:42425/lol-champ-select/v1/session");
            client.Timeout = -1;
            var request = new RestRequest("");
            request.AddHeader("Accept", "application/json");
            request.AddHeader("Authorization", "Basic cmlvdDp2ZFNCV0V6M0dRMWFVYzZNdGZPZVZn");
            var response = await client.GetAsync<ResponseModel>(request);
            var myActorCellId = response.LocalPlayerCellId;
            if (response.Actions == null)
            {
                return new ChampionPicksTurnAndPosition(new List<ChampionPick>(), false, "");
            } else
            {
                if (response.Actions.Count > 2)
                {
                    var actions = response.Actions.Skip(2).SelectMany(d => d).ToList(); ;
                    var picks = new List<ChampionPick>();
                    var retval = new ChampionPicksTurnAndPosition(picks, false, "");
                    foreach (var action in actions)
                    {
                        if (action.ChampionId == 0)
                        {
                            continue;
                        }
                        ChampionPick championPick = new ChampionPick();
                        if (action.ActorCellId == myActorCellId)
                        {
                            championPick.myChamp = true;
                            if (action.IsInProgress)
                            {
                                retval.myTurn = true;
                            }
                        } else
                        {
                            championPick.myChamp = false;
                        }
                        championPick.myTeam = action.IsAllyAction;
                        championPick.champion = idToName[Convert.ToInt32(action.ChampionId)];
                        picks.Add(championPick);
                    }
                    retval.position = riotToLocalPosition[response.MyTeam.First(teammate => teammate.CellId == myActorCellId).AssignedPosition];
                    return retval;
                } else
                {
                    return new ChampionPicksTurnAndPosition(new List<ChampionPick>(), false, "");
                }
            }
        }

        static void Main(string[] args)
        {
            try
            {
                ClientCommunicator clientCommunicator = new ClientCommunicator();
                Task<ChampionPicksTurnAndPosition> retval = clientCommunicator.getChampionsPicked();
                retval.Wait();
                var result = retval.Result;
                Console.WriteLine(retval.Result);
            } catch (Exception e)
            {
                Console.WriteLine(e);
            }
        }
    }
}
