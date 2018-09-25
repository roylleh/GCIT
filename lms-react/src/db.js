export const endpoint = 'http://mohammad-lms.us-east-1.elasticbeanstalk.com/';

export async function getCollection(collection)
{
    if (document.cookie === '1' && collection === 'bookLoans')
        collection = 'bookLoans/cardNo/1';

    const res = await fetch(endpoint + collection, {});
    const json = await res.json();
    return json;
}

export async function postElement(collection, data)
{
    const obj =
    {
        method: 'POST',
        headers:
        {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };

    await fetch(endpoint + collection, obj);
    if (document.cookie === '1' && collection === 'bookLoan')
    {
        const element = document.getElementById('row_' + data.bookId + '_' + data.branchId);
        if (element !== null)
        {
            element.className = 'table-success';
            await sleep(500);
            element.className = '';
        }
    }
}

export async function putElement(collection, i, data)
{
    const obj =
    {
        method: 'PUT',
        headers:
        {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };

    await fetch(endpoint + collection, obj);
    const element = document.getElementById('row_' + i);
    if (element !== null)
    {
        element.className = 'table-success';
        await sleep(500);
        element.className = '';
    }
}

export async function deleteElement(collectionId)
{
    const obj =
    {
        method: 'DELETE',
        headers:
        {
            'Content-Type': 'application/json'
        }
    };

    await fetch(endpoint + collectionId, obj);
}

function sleep(ms)
{
    return new Promise(res => setTimeout(res, ms));
}