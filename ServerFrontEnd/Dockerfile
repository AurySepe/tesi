FROM mcr.microsoft.com/dotnet/aspnet:6.0 AS base
WORKDIR /app
EXPOSE 80
EXPOSE 443

FROM mcr.microsoft.com/dotnet/sdk:6.0 AS build
WORKDIR /src
COPY ["ServerFrontEnd/ServerFrontEnd.csproj", "ServerFrontEnd/"]
RUN dotnet restore "ServerFrontEnd/ServerFrontEnd.csproj"
COPY . .
WORKDIR "/src/ServerFrontEnd"
RUN dotnet build "ServerFrontEnd.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "ServerFrontEnd.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "ServerFrontEnd.dll"]
