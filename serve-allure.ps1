# serve-allure.ps1 - Run tests, generate Allure results, serve the report and print the URL
$ErrorActionPreference = 'Stop'

mvn clean test
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
Write-Host "Generating Allure report..."
mvn allure:generate

$log = Join-Path $PSScriptRoot 'serve.log'
if (Test-Path $log) { Remove-Item $log -Force }

$proc = Start-Process -FilePath 'mvn' -ArgumentList 'allure:serve' -NoNewWindow -RedirectStandardOutput $log -RedirectStandardError $log -PassThru
Write-Host "Started Allure server (PID $($proc.Id)). Waiting for URL..."

$maxAttempts = 30
for ($i = 0; $i -lt $maxAttempts; $i++) {
    Start-Sleep -Seconds 1
    if (Test-Path $log) {
        $content = Get-Content $log -Raw -ErrorAction SilentlyContinue
        if ($content -match 'http://[\w\d:\./-]+') {
            $url = $Matches[0]
            Write-Host "Allure URL: $url"
            try { Start-Process $url } catch { }
            exit 0
        }
    }
}

Write-Host "Could not find Allure URL in $log; check the log file for details."
Write-Host "You can stop serving with: Stop-Process -Id $($proc.Id)"