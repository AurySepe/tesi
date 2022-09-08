FROM mcr.microsoft.com/dotnet/aspnet:6.0 AS base
WORKDIR /app
EXPOSE 80
EXPOSE 443

FROM mcr.microsoft.com/dotnet/sdk:6.0 AS build
WORKDIR /src
COPY ["MessageConverter/MessageConverter.csproj", "MessageConverter/"]
RUN dotnet restore "MessageConverter/MessageConverter.csproj"
COPY . .
WORKDIR "/src/MessageConverter"
RUN dotnet build "MessageConverter.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "MessageConverter.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "MessageConverter.dll"]
