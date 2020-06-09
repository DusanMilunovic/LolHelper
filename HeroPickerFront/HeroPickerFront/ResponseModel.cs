using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System;
using System.Collections.Generic;

using System.Globalization;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace HeroPickerFront
{
    public partial class ResponseModel
    {
        [JsonProperty("actions")]
        public List<List<Action>> Actions { get; set; }

        [JsonProperty("allowBattleBoost")]
        public bool AllowBattleBoost { get; set; }

        [JsonProperty("allowDuplicatePicks")]
        public bool AllowDuplicatePicks { get; set; }

        [JsonProperty("allowLockedEvents")]
        public bool AllowLockedEvents { get; set; }

        [JsonProperty("allowRerolling")]
        public bool AllowRerolling { get; set; }

        [JsonProperty("allowSkinSelection")]
        public bool AllowSkinSelection { get; set; }

        [JsonProperty("bans")]
        public Bans Bans { get; set; }

        [JsonProperty("benchChampionIds")]
        public List<object> BenchChampionIds { get; set; }

        [JsonProperty("benchEnabled")]
        public bool BenchEnabled { get; set; }

        [JsonProperty("boostableSkinCount")]
        public long BoostableSkinCount { get; set; }

        [JsonProperty("chatDetails")]
        public ChatDetails ChatDetails { get; set; }

        [JsonProperty("counter")]
        public long Counter { get; set; }

        [JsonProperty("entitledFeatureState")]
        public EntitledFeatureState EntitledFeatureState { get; set; }

        [JsonProperty("hasSimultaneousBans")]
        public bool HasSimultaneousBans { get; set; }

        [JsonProperty("hasSimultaneousPicks")]
        public bool HasSimultaneousPicks { get; set; }

        [JsonProperty("isCustomGame")]
        public bool IsCustomGame { get; set; }

        [JsonProperty("isSpectating")]
        public bool IsSpectating { get; set; }

        [JsonProperty("localPlayerCellId")]
        public long LocalPlayerCellId { get; set; }

        [JsonProperty("lockedEventIndex")]
        public long LockedEventIndex { get; set; }

        [JsonProperty("myTeam")]
        public List<Team> MyTeam { get; set; }

        [JsonProperty("rerollsRemaining")]
        public long RerollsRemaining { get; set; }

        [JsonProperty("skipChampionSelect")]
        public bool SkipChampionSelect { get; set; }

        [JsonProperty("theirTeam")]
        public List<Team> TheirTeam { get; set; }

        [JsonProperty("timer")]
        public Timer Timer { get; set; }

        [JsonProperty("trades")]
        public List<Trade> Trades { get; set; }
    }

    public partial class Action
    {
        [JsonProperty("actorCellId")]
        public long ActorCellId { get; set; }

        [JsonProperty("championId")]
        public long ChampionId { get; set; }

        [JsonProperty("completed")]
        public bool Completed { get; set; }

        [JsonProperty("id")]
        public long Id { get; set; }

        [JsonProperty("isAllyAction")]
        public bool IsAllyAction { get; set; }

        [JsonProperty("isInProgress")]
        public bool IsInProgress { get; set; }

        [JsonProperty("type")]
        public String Type { get; set; }
    }

    public partial class Bans
    {
        [JsonProperty("myTeamBans")]
        public List<object> MyTeamBans { get; set; }

        [JsonProperty("numBans")]
        public long NumBans { get; set; }

        [JsonProperty("theirTeamBans")]
        public List<object> TheirTeamBans { get; set; }
    }

    public partial class ChatDetails
    {
        [JsonProperty("chatRoomName")]
        public string ChatRoomName { get; set; }

        [JsonProperty("chatRoomPassword")]
        public object ChatRoomPassword { get; set; }
    }

    public partial class EntitledFeatureState
    {
        [JsonProperty("additionalRerolls")]
        public long AdditionalRerolls { get; set; }

        [JsonProperty("unlockedSkinIds")]
        public List<object> UnlockedSkinIds { get; set; }
    }

    public partial class Team
    {
        [JsonProperty("assignedPosition")]
        public string AssignedPosition { get; set; }

        [JsonProperty("cellId")]
        public long CellId { get; set; }

        [JsonProperty("championId")]
        public long ChampionId { get; set; }

        [JsonProperty("championPickIntent")]
        public long ChampionPickIntent { get; set; }

        [JsonProperty("entitledFeatureType")]
        public String EntitledFeatureType { get; set; }

        [JsonProperty("selectedSkinId")]
        public long SelectedSkinId { get; set; }

        [JsonProperty("spell1Id")]
        public long Spell1Id { get; set; }

        [JsonProperty("spell2Id")]
        public long Spell2Id { get; set; }

        [JsonProperty("summonerId")]
        public long SummonerId { get; set; }

        [JsonProperty("team")]
        public long TeamTeam { get; set; }

        [JsonProperty("wardSkinId")]
        public long WardSkinId { get; set; }
    }

    public partial class Timer
    {
        [JsonProperty("adjustedTimeLeftInPhase")]
        public long AdjustedTimeLeftInPhase { get; set; }

        [JsonProperty("internalNowInEpochMs")]
        public long InternalNowInEpochMs { get; set; }

        [JsonProperty("isInfinite")]
        public bool IsInfinite { get; set; }

        [JsonProperty("phase")]
        public string Phase { get; set; }

        [JsonProperty("totalTimeInPhase")]
        public long TotalTimeInPhase { get; set; }
    }

    public partial class Trade
    {
        [JsonProperty("cellId")]
        public long CellId { get; set; }

        [JsonProperty("id")]
        public long Id { get; set; }

        [JsonProperty("state")]
        public string State { get; set; }
    }

    public enum TypeEnum { Ban, Pick, TenBansReveal };

    public enum EntitledFeatureType { Empty, None };

    internal static class Converter
    {
        public static readonly JsonSerializerSettings Settings = new JsonSerializerSettings
        {
            MetadataPropertyHandling = MetadataPropertyHandling.Ignore,
            DateParseHandling = DateParseHandling.None,
            Converters =
            {
                TypeEnumConverter.Singleton,
                EntitledFeatureTypeConverter.Singleton,
                new IsoDateTimeConverter { DateTimeStyles = DateTimeStyles.AssumeUniversal }
            },
        };
    }

    internal class TypeEnumConverter : JsonConverter
    {
        public override bool CanConvert(Type t) => t == typeof(TypeEnum) || t == typeof(TypeEnum?);

        public override object ReadJson(JsonReader reader, Type t, object existingValue, JsonSerializer serializer)
        {
            if (reader.TokenType == JsonToken.Null) return null;
            var value = serializer.Deserialize<string>(reader);
            switch (value)
            {
                case "ban":
                    return TypeEnum.Ban;
                case "pick":
                    return TypeEnum.Pick;
                case "ten_bans_reveal":
                    return TypeEnum.TenBansReveal;
            }
            throw new Exception("Cannot unmarshal type TypeEnum");
        }

        public override void WriteJson(JsonWriter writer, object untypedValue, JsonSerializer serializer)
        {
            if (untypedValue == null)
            {
                serializer.Serialize(writer, null);
                return;
            }
            var value = (TypeEnum)untypedValue;
            switch (value)
            {
                case TypeEnum.Ban:
                    serializer.Serialize(writer, "ban");
                    return;
                case TypeEnum.Pick:
                    serializer.Serialize(writer, "pick");
                    return;
                case TypeEnum.TenBansReveal:
                    serializer.Serialize(writer, "ten_bans_reveal");
                    return;
            }
            throw new Exception("Cannot marshal type TypeEnum");
        }

        public static readonly TypeEnumConverter Singleton = new TypeEnumConverter();
    }

    internal class EntitledFeatureTypeConverter : JsonConverter
    {
        public override bool CanConvert(Type t) => t == typeof(EntitledFeatureType) || t == typeof(EntitledFeatureType?);

        public override object ReadJson(JsonReader reader, Type t, object existingValue, JsonSerializer serializer)
        {
            if (reader.TokenType == JsonToken.Null) return null;
            var value = serializer.Deserialize<string>(reader);
            switch (value)
            {
                case "":
                    return EntitledFeatureType.Empty;
                case "NONE":
                    return EntitledFeatureType.None;
            }
            throw new Exception("Cannot unmarshal type EntitledFeatureType");
        }

        public override void WriteJson(JsonWriter writer, object untypedValue, JsonSerializer serializer)
        {
            if (untypedValue == null)
            {
                serializer.Serialize(writer, null);
                return;
            }
            var value = (EntitledFeatureType)untypedValue;
            switch (value)
            {
                case EntitledFeatureType.Empty:
                    serializer.Serialize(writer, "");
                    return;
                case EntitledFeatureType.None:
                    serializer.Serialize(writer, "NONE");
                    return;
            }
            throw new Exception("Cannot marshal type EntitledFeatureType");
        }

        public static readonly EntitledFeatureTypeConverter Singleton = new EntitledFeatureTypeConverter();
    }
}
